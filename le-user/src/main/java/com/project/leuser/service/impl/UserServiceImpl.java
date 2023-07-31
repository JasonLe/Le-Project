package com.project.leuser.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.lecommon.enums.ExceptionEnum;
import com.project.lecommon.exception.MyException;
import com.project.leuser.dto.request.LoginDTO;
import com.project.leuser.dto.request.RegisterDTO;
import com.project.leuser.dto.response.TokenResponse;
import com.project.leuser.entity.User;
import com.project.leuser.enums.RedisKeyEnums;
import com.project.leuser.mapper.UserMapper;
import com.project.leuser.service.UserService;
import com.project.leuser.utils.JWTUtil;
import com.project.leuser.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public TokenResponse login(LoginDTO loginDTO) {

        if (!Validator.isEmail(loginDTO.getEmail())) {
            throw new MyException(ExceptionEnum.ACCOUNT_PWD_ERROR);
        }

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select()
                .eq("email", loginDTO.getEmail());
        User user = userMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(user)) {
            throw new MyException(ExceptionEnum.USER_NOT_EXISTS);
        }

        if (!user.getPassword().equals(encodePwd(loginDTO.getEmail(), loginDTO.getPassword()))) {
            throw new MyException(ExceptionEnum.ACCOUNT_PWD_ERROR);
        }

        redisUtil.set(RedisKeyEnums.USER_INFO_PREFIX + user.getEmail(), user);
        String token = JWTUtil.generateToken(user);

        return TokenResponse.builder().leToken(token).user(user).build();
    }

    /**
     * 密码逻辑 email|password
     **/
    private String encodePwd(String email, String password) {
        return DigestUtil.md5Hex(email + "|" + password);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(RegisterDTO registerDTO) {
        // 注册入参校验
        userVerification(registerDTO);

        User registerUser = new User();
        BeanUtil.copyProperties(registerDTO, registerUser);

        registerUser.setPassword(encodePwd(registerUser.getEmail(), registerUser.getPassword()));
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());
        int count = userMapper.insert(registerUser);
        return count == 1;
    }

    /**
     * 注册入参校验
     **/
    private void userVerification(RegisterDTO registerDTO) {
        if (ObjectUtil.isNotEmpty(registerDTO.getMobile()) && !Validator.isMobile(registerDTO.getMobile())) {
            throw new MyException("手机号格式错误");
        }
        if (!Validator.isEmail(registerDTO.getEmail())) {
            throw new MyException("邮箱格式错误");
        }
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectById(id);
    }
}

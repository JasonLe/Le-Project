package com.project.leuser.service;

import com.project.leuser.dto.request.LoginDTO;
import com.project.leuser.dto.request.RegisterDTO;
import com.project.leuser.dto.response.TokenResponse;
import com.project.leuser.entity.User;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
public interface UserService {
    /**
     * @author whl
     * @description 登录接口
     * @date 2023/7/12
     * @param loginDTO
     * @return User
     **/
    TokenResponse login(LoginDTO loginDTO);

    User getUser(Integer id);

    Boolean register(RegisterDTO registerDTO);
}

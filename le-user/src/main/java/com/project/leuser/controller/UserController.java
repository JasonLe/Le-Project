package com.project.leuser.controller;

import com.project.lecommon.enums.ExceptionEnum;
import com.project.lecommon.result.R;
import com.project.leuser.annotation.AuthType;
import com.project.leuser.annotation.NeedAuth;
import com.project.leuser.dto.request.LoginDTO;
import com.project.leuser.dto.request.RegisterDTO;
import com.project.leuser.dto.response.TokenResponse;
import com.project.leuser.entity.User;
import com.project.leuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@RestController
@RequestMapping("/le-user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    R<TokenResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        return R.success(userService.login(loginDTO));
    }

    @PostMapping("/register")
    R<Boolean> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return R.isSuccess(userService.register(registerDTO), ExceptionEnum.REGISTER_ERROR);
    }

    /**
     * 获取用户信息
     **/
    @NeedAuth(AuthType.UNNECESSARY)
    @PostMapping("/get/user")
    R<User> getUser(@RequestParam(name = "id") Integer id) {
        return R.success(userService.getUser(id));
    }
}

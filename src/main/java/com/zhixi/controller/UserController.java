package com.zhixi.controller;

import com.zhixi.exception.ExceptionCodeEnum;
import com.zhixi.exception.utils.Result;
import com.zhixi.pojo.User;
import com.zhixi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName UserController
 * @Author zhangzhixi
 * @Description
 * @Date 2022-4-7 12:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @RequestMapping("/selectUserById/{id}")
    public Result<User> selectUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }


    /**
     * 插入用户
     *
     * @param userPojo 用户信息
     * @return 是否成功
     */
    @PostMapping("insertUser")
    public Result<Boolean> insertUser(@RequestBody User userPojo) {
        if (userPojo == null) {
            // 只传入定义好的错误
            return Result.error(ExceptionCodeEnum.EMPTY_PARAM);
        }
        if (userPojo.getUserName().contains("死")) {
            // 抛出自定义的错误信息
            return Result.error(ExceptionCodeEnum.ERROR_PARAM, "用户名不能包含特殊字符");
        }
        if (userPojo.getUserAge() < 18) {
            // 抛出自定义的错误信息
            return Result.error("年龄不能小于18");
        }

        return Result.success(userService.save(userPojo));
    }
}





























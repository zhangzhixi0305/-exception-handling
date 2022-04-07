package com.zhixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhixi.pojo.User;
import com.zhixi.service.UserService;
import com.zhixi.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangzhixi
* @description 针对表【user(user)】的数据库操作Service实现
* @createDate 2022-04-07 12:19:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}





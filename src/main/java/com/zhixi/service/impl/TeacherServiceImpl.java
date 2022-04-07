package com.zhixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhixi.pojo.Teacher;
import com.zhixi.service.TeacherService;
import com.zhixi.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangzhixi
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2022-04-07 12:19:36
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}





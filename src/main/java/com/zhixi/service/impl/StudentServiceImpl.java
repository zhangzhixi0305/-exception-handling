package com.zhixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhixi.pojo.Student;
import com.zhixi.service.StudentService;
import com.zhixi.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangzhixi
* @description 针对表【student】的数据库操作Service实现
* @createDate 2022-04-07 12:19:36
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}





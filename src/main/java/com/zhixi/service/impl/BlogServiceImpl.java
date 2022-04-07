package com.zhixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhixi.pojo.Blog;
import com.zhixi.service.BlogService;
import com.zhixi.mapper.BlogMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangzhixi
* @description 针对表【blog】的数据库操作Service实现
* @createDate 2022-04-07 12:19:36
*/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{

}





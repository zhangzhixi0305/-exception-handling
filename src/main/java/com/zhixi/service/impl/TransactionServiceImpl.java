package com.zhixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhixi.pojo.Transaction;
import com.zhixi.service.TransactionService;
import com.zhixi.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangzhixi
* @description 针对表【transaction】的数据库操作Service实现
* @createDate 2022-04-07 12:19:36
*/
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction>
    implements TransactionService{

}





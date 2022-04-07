package com.zhixi.controller;

import com.zhixi.pojo.Transaction;
import com.zhixi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TranController
 * @Author zhangzhixi
 * @Description
 * @Date 2022-4-7 12:34
 * @Version 1.0
 */
@RestController()
@RequestMapping("/tran")
public class TranController {

    @Autowired
    private TransactionService transactionService;

    // localhost/tran/getTranByTranId/1
    @RequestMapping("/getTranByTranId/{id}")
    public Transaction getTranByTranId(@PathVariable("id") Long tranId) {
        return transactionService.getById(tranId);
    }
}

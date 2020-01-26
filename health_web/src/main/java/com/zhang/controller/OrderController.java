package com.zhang.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = orderService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        return pageResult;
    }
}

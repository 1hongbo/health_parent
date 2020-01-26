package com.zhang.service;

import com.zhang.entity.PageResult;
import com.zhang.entity.Result;

import java.util.List;
import java.util.Map;

public interface OrderService {
    //体检预约
    public Result submitOrder(Map map);
    public Result findById(int id) throws Exception;
    public List<Map> getSetmealReport();
    public PageResult pageQuery(Integer currentPage, Integer pageSize);
}

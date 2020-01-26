package com.zhang.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.constant.MessageConstant;
import com.zhang.entity.Result;
import com.zhang.pojo.Setmeal;
import com.zhang.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    //查询所有的套餐信息
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        List<Setmeal> list = setmealService.findAll();
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
    }

    //根据套餐id查询套餐详情
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmeal);
    }
}

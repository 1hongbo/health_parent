package com.zhang.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.constant.MessageConstant;
import com.zhang.constant.RedisConstant;
import com.zhang.entity.PageResult;
import com.zhang.entity.QueryPageBean;
import com.zhang.entity.Result;
import com.zhang.pojo.Setmeal;
import com.zhang.service.SetmealService;
import com.zhang.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    //文件上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){//1.jpg
        //获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        //获得.的位置索引
        int index = originalFilename.lastIndexOf(".");
        //截取后缀
        String suffix = originalFilename.substring(index);
        //拼接一个以UUID动态构造的文件名，放在冲突
        String fileName = UUID.randomUUID().toString() + suffix;
        try {
            //调用七牛云提供的SDK实现文件上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //将文件名称保存到Redis集合中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //新增套餐
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            setmealService.add(setmeal,checkgroupIds);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }
}

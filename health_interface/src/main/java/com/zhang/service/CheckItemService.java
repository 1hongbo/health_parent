package com.zhang.service;
import com.zhang.entity.PageResult;
import com.zhang.pojo.CheckItem;

import java.util.List;

//检查项服务接口
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    public void deleteById(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public void delete(Integer[] ids);
    public List<CheckItem> findAll();
    public Integer[] findCheckItemIdsByCheckGroupId(Integer checkGroupId);
}

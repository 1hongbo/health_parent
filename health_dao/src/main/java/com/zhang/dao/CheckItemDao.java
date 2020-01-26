package com.zhang.dao;

import com.github.pagehelper.Page;
import com.zhang.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> pageQuery(String queryString);
    public void deleteById(Integer id);
    public long findCountByCheckItemId(Integer checkItemId);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
    public Integer[] findCheckItemIdsByCheckGroupId(Integer checkGroupId);
}

package com.milo.dao;

import com.github.pagehelper.Page;
import com.milo.domain.CheckItem;

import java.util.List;

public interface CheckItemDao {

    //添加检查项
    public void addCheckItem(CheckItem checkItem);

    //条件分页查询
    Page<CheckItem> queryCondition(String queryString);

    //根据checkitemID查询t_checkgroup_checkitem
    public List<Integer> findRel(Integer id);

    //根据id删除checkitem
    public void delById(Integer id);

    //根据checkitemIds查询
    public List<Integer> findBatchConditons(Integer[] checkItemIds);

    //批量删除
    public void delBatch(Integer[] checkItemIds);

    //查询一个
    public CheckItem findOne(Integer id);

    //修改数据
    public void update(CheckItem checkItem);

    //查询所有
    public List<CheckItem> findAll();
}

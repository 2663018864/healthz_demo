package com.milo;

import com.milo.domain.CheckItem;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;

import java.util.List;

public interface CheckItemService {

    //添加检查项
    public void addCheckItem(CheckItem checkItem);

    //条件分页查询
    public PageResult queryPage(QueryPageBean queryPageBean);

    //根据id删除
    public void delById(Integer id);

    //批量删除
    public void delMul(Integer[] checkItemIds);

    //查询一个
    public CheckItem findOne(Integer id);

    //修改数据
    public void update(CheckItem checkItem);

    //查询所有
    public List<CheckItem> findAll();

}

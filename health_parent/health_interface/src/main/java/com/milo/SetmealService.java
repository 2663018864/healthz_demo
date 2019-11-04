package com.milo;

import com.milo.domain.Setmeal;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;

public interface SetmealService {

    //添加检查套餐
    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    //分页条件查询
    public PageResult findPage(QueryPageBean queryPageBean);
}

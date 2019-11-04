package com.milo.dao;

import com.github.pagehelper.Page;
import com.milo.domain.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {

    //添加检查套餐
    public void insertSetmeal(Setmeal setmeal);

    //添加检查套餐与检查组的关系
    public void insertCondition(@Param("setmealId") Integer setmealId, @Param("checkgroupIds") Integer[] checkgroupIds);

    //条件查询
    public List<Setmeal> queryCondition(String queryString);
}

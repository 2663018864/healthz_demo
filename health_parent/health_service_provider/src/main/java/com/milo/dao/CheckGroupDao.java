package com.milo.dao;

import com.github.pagehelper.Page;
import com.milo.domain.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    //添加检查组于检查项的关系
    public void addCheckItemRel(Map map);

    //添加检查组
    public void add(CheckGroup checkGroup);

    //分页条件查询
    public Page<CheckGroup> queryCondition(String queryString);

    //查询一个检查组的信息
    public CheckGroup findOne(Integer checkGroupId);

    //根据检查组的id查询检查项的id
    public List<Integer> findCheckitemIdsByCheckGroupId(Integer checkGroupId);

    //根据检查组的id修改检查组
    public void updateCheckGroup(CheckGroup checkGroup);

    //根据检查组id删除检查组和检查项的关系
    public void deleteCheckItemIdAndCheckGroupId(Integer id);

    //根据检查组的id添加关系
    public void insertCondition(Map map);

    //查询所有
    public List<CheckGroup> findAll();
}

package com.milo;

import com.milo.domain.CheckGroup;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;

import java.util.List;

public interface CheckGroupService {
    //添加检查组
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    //分页条件查询
    public PageResult findPage(QueryPageBean queryPageBean);

    //查询一个检查组的信息
    public CheckGroup findOne(Integer checkGroupId);

    //根据检查组的id查询关联的检查项的id
    public List<Integer> findCheckitemIdsByCheckGroupId(Integer checkGroupId);

    //编辑检查组
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    //查询所有
    public List<CheckGroup> findAll();
}

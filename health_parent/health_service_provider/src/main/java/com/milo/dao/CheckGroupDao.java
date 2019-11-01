package com.milo.dao;

import com.milo.domain.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    //添加检查组于检查项的关系
    public void addCheckItemRel(Map map);

    //添加检查组
    public void add(CheckGroup checkGroup);
}

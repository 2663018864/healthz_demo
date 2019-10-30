package com.milo.dao;

import com.milo.domain.Emp;

import java.util.List;

public interface EmpDao {

    //添加员工
    public void saveEmp(Emp emp);

    //根据id修改员工
    public void updateById(Emp emp);

    //根据id删除员工
    public void deleteById(int id);

    //根据id查询员工
    public Emp findById(int id);

    //查询所有员工
    public List<Emp> findAll();
}

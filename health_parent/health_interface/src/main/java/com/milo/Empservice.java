package com.milo;

import com.milo.domain.Emp;

import java.util.List;

public interface Empservice {

    //添加员工
    public void saveEmp(Emp emp);

    //修改员工
    public void updateEmp(Emp emp);

    //删除员工
    public void deleteEmp(int id);

    //查询一个员工
    public Emp findOneEmp(int id);

    //查询所有员工
    public List<Emp> findAll();
}

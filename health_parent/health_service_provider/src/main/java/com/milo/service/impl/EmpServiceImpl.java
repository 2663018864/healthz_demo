package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.milo.Empservice;
import com.milo.dao.EmpDao;
import com.milo.domain.Emp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = Empservice.class)
public class EmpServiceImpl implements Empservice {

    @Autowired
    private EmpDao empDao;

    @Override
    public void saveEmp(Emp emp) {
        empDao.saveEmp(emp);
    }

    @Override
    public void updateEmp(Emp emp) {
        empDao.updateById(emp);
    }

    @Override
    public void deleteEmp(int id) {
        empDao.deleteById(id);
    }

    @Override
    public Emp findOneEmp(int id) {
        return empDao.findById(id);
    }

    @Override
    public List<Emp> findAll() {
        return empDao.findAll();
    }
}

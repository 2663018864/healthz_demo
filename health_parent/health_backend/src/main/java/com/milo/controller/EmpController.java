package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.Empservice;
import com.milo.domain.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/emp")
public class EmpController {

    @Reference
    private Empservice empservice;

    @RequestMapping("/save")
    public void save(@RequestBody Emp emp){
        empservice.saveEmp(emp);
    }
    @RequestMapping("/update")
    public void update(@RequestBody Emp emp){
        System.out.println(emp);
        empservice.updateEmp(emp);
    }
    @RequestMapping("/delete")
    public void delete(int id){
        empservice.deleteEmp(id);
    }
    @RequestMapping("/findOne")
    public Emp findOne(int id){
        return empservice.findOneEmp(id);
    }
    @RequestMapping("/findAll")
    public List<Emp> findAll(){
        List<Emp> list = empservice.findAll();
//        System.out.println(list);
        return list;
    }

}

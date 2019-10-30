package com.milo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Emp implements Serializable {
    private Integer id;
    private String name;
    private Double salary;
    //fastjson处理日期格式
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Emp{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }
}

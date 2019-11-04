package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.milo.SetmealService;
import com.milo.constant.RedisConstant;
import com.milo.dao.SetmealDao;
import com.milo.domain.Setmeal;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    //添加检查套餐
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //添加检查套餐的基本信息
        setmealDao.insertSetmeal(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            //添加检查套餐和检查组的关系
            setmealDao.insertCondition(setmeal.getId(), checkgroupIds);
        }
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
    }

    //分页条件查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取分页参数
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //开启分页查询,threadLocal线程
        PageHelper.startPage(currentPage,pageSize);
        //调用dao查询
        List<Setmeal> setmeals = setmealDao.queryCondition(queryString);
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmeals);
        //封装数据
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }
}

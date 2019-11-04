package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.milo.CheckGroupService;
import com.milo.dao.CheckGroupDao;
import com.milo.domain.CheckGroup;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    //添加检查组
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);

        Map<String, Integer> map = new HashMap<>();
        for (Integer checkitemId : checkitemIds) {
            map.put("checkgroup_id", checkGroup.getId());
            map.put("checkitem_id", checkitemId);
            checkGroupDao.addCheckItemRel(map);
        }
    }

    //分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //使用分页助手
        PageHelper.startPage(currentPage, pageSize);
        //调用dao方法
        Page<CheckGroup> page = checkGroupDao.queryCondition(queryString);
        //封装数据
        return new PageResult(page.getTotal(), page.getResult());
    }

    //查询一个检查组的信息
    @Override
    public CheckGroup findOne(Integer checkGroupId) {
        return checkGroupDao.findOne(checkGroupId);
    }

    //根据检查组的id查询检查项的id
    @Override
    public List<Integer> findCheckitemIdsByCheckGroupId(Integer checkGroupId) {
        return checkGroupDao.findCheckitemIdsByCheckGroupId(checkGroupId);
    }

    //编辑检查组
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组的基本信息
        checkGroupDao.updateCheckGroup(checkGroup);
        //根据检查组的id删除关系
        checkGroupDao.deleteCheckItemIdAndCheckGroupId(checkGroup.getId());
        //添加新的关系
        Map<String, Object> map = new HashMap<>();
        map.put("checkGroupId", checkGroup.getId());
        for (Integer checkitemId : checkitemIds) {
            map.put("checkitemId", checkitemId);
            checkGroupDao.insertCondition(map);
        }
    }

    //查询所有
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

}

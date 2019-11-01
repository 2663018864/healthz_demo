package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.milo.CheckItemService;
import com.milo.dao.CheckItemDao;
import com.milo.domain.CheckItem;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    //添加检查项
    @Override
    public void addCheckItem(CheckItem checkItem) {
        checkItemDao.addCheckItem(checkItem);
    }

    //条件分页查询
    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //开启分页查询,绑定线程
        PageHelper.startPage(currentPage, pageSize);
        //调用dao层查询数据
        Page<CheckItem> page = checkItemDao.queryCondition(queryString);
        //封装数据
        List<CheckItem> rows = page.getResult();
        long total = page.getTotal();
        return new PageResult(total, rows);
    }

    //根据id删除
    @Override
    public void delById(Integer id) {
        //先查询关系表
        List<Integer> checkgroup_ids = checkItemDao.findRel(id);
        if (checkgroup_ids != null && checkgroup_ids.size() > 0){
            throw new RuntimeException("有外键约束存在，不能删除");
        }
        checkItemDao.delById(id);
    }

    //批量删除
    @Override
    public void delMul(Integer[] checkItemIds) {
         //先查询关系表
        List<Integer> batchConditons = checkItemDao.findBatchConditons(checkItemIds);
        if (batchConditons != null && batchConditons.size() > 0) {
            throw new RuntimeException("有外键约束存在，不能删除");
        }
        checkItemDao.delBatch(checkItemIds);
    }

    //查询一个
    @Override
    public CheckItem findOne(Integer id) {
        return checkItemDao.findOne(id);
    }

    //修改数据
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    //查询所有
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}

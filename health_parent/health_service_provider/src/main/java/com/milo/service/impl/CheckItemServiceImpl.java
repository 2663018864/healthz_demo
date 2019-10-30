package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.milo.CheckItemService;
import com.milo.dao.CheckItemDao;
import com.milo.domain.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
}

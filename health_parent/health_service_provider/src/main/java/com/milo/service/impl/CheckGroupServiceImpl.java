package com.milo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.milo.CheckGroupService;
import com.milo.dao.CheckGroupDao;
import com.milo.domain.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
}

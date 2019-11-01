package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.CheckGroupService;
import com.milo.constant.MessageConstant;
import com.milo.domain.CheckGroup;
import com.milo.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    //添加检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try{
            checkGroupService.add(checkGroup, checkitemIds);
            Result result = new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
            return result;
        }
    }
}

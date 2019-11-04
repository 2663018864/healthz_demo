package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.CheckGroupService;
import com.milo.constant.MessageConstant;
import com.milo.domain.CheckGroup;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import com.milo.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    //回显单个检查组的信息
    @RequestMapping("/findOne")
    public Result findOne(Integer checkGroupId){
        try {
            CheckGroup one = checkGroupService.findOne(checkGroupId);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, one);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //根据检查组id查询检查组关联的检查项id
    @RequestMapping("/findCheckitemIdsByCheckGroupId")
    public List<Integer> findCheckitemIdsByCheckGroupId(Integer checkGroupId) {
        return checkGroupService.findCheckitemIdsByCheckGroupId(checkGroupId);
    }

    //编辑检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try{
            checkGroupService.edit(checkGroup, checkitemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    //查询所有
    @RequestMapping("/findAll")
    public List<CheckGroup> findAll() {
        return checkGroupService.findAll();
    }
}

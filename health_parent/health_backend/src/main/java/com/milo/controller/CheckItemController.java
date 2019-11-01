package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.CheckItemService;
import com.milo.constant.MessageConstant;
import com.milo.domain.CheckItem;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import com.milo.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    //添加CheckItem
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.addCheckItem(checkItem);
            Result result = new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
            return result;
        }
    }

    //查询CheckItem
    @RequestMapping("/queryPage")
    public PageResult queryPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkItemService.queryPage(queryPageBean);
        return pageResult;
    }

    //删除CheckItem
    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            checkItemService.delById(id);
            Result result = new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
            return result;
        }
    }

    //批量删除
    @RequestMapping("/delMul")
    public Result delMul(Integer[] checkItemIds){
//        System.out.println(checkItemIds);
        try {
            checkItemService.delMul(checkItemIds);
            Result result = new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
            return result;
        }
    }
    //回显数据
    @RequestMapping("/findOne")
    public Result findOne(Integer id){
        try {
            CheckItem checkItem = checkItemService.findOne(id);
            Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
            return result;
        }
    }
    //修改数据
    @RequestMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        try {
            checkItemService.update(checkItem);
            Result result = new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
            return result;
        }
    }

    //查询所有
    @RequestMapping("/findAll")
    public List<CheckItem> findAll(){
        return checkItemService.findAll();
    }
}

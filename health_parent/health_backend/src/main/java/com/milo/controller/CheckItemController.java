package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.CheckItemService;
import com.milo.constant.MessageConstant;
import com.milo.domain.CheckItem;
import com.milo.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

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
}

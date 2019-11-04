package com.milo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.milo.SetmealService;
import com.milo.constant.MessageConstant;
import com.milo.constant.RedisConstant;
import com.milo.domain.Setmeal;
import com.milo.entity.PageResult;
import com.milo.entity.QueryPageBean;
import com.milo.entity.Result;
import com.milo.utils.ALiYunUtils;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    //上传图片
    @RequestMapping("/upload")
    public Result uploadImg(MultipartFile imgFile) {
        try{
            //获取文件名
            String originalFilename = imgFile.getOriginalFilename();
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //获取文件的后缀，没有“.”
            String extension = FilenameUtils.getExtension(originalFilename);
            //生成随机的文件名
            String fileNam = UUID.randomUUID().toString() +"."+ extension;
            //上传图片到阿里云
            ALiYunUtils.uplodaFile(fileNam, imgFile.getBytes());
            //上传图片名称到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileNam);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileNam);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //添加检查套餐
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.add(setmeal, checkgroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    //分页条件查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }
}

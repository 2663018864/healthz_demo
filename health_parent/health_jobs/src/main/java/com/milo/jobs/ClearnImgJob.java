package com.milo.jobs;

import com.milo.constant.RedisConstant;
import com.milo.utils.ALiYunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearnImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void delImg() {
        System.out.println("调度器开始执行");
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        System.out.println(set);
        if (set != null && set.size() > 0) {
            for (String fileName : set) {
                //从阿里云中删除垃圾图片
                ALiYunUtils.delFile(fileName);
                System.out.println("在阿里云中删除图片");
                //删除redis中的图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
                System.out.println("在redis中删除图片");
            }
        }
    }
}

package com.milo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ALiYunUtils {
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;

    static{
        InputStream is = ALiYunUtils.class.getClassLoader().getResourceAsStream("oss.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            endpoint = prop.getProperty("oss.endpoint");
            accessKeyId = prop.getProperty("oss.accessKeyId");
            accessKeySecret = prop.getProperty("oss.accessKeySecret");
            bucketName = prop.getProperty("oss.bucketName");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uplodaFile(String fileName, byte[] content) {
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void delFile(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, fileName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}

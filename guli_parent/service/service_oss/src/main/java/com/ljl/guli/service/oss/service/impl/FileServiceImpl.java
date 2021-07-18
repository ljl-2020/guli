/**
 * FileName: FileServiceImpl
 * Author: ljl
 * Date: 2021/5/15 11:44
 * Description:
 * History:
 */


package com.ljl.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.ljl.guli.service.oss.service.FileService;
import com.ljl.guli.service.oss.utils.OssPropertity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service

public class FileServiceImpl implements FileService {

    //用于读取信息
    @Autowired
    private OssPropertity ossPropertity;

    @Override
    public String upload(InputStream inputStream, String module, String originalFileName) {
        String endpoint = ossPropertity.getEndpoint();
        String bucketname = ossPropertity.getBucketname();
        String keyid = ossPropertity.getKeyid();
        String keysecret = ossPropertity.getKeysecret();
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint,keyid,keysecret);
        //判断ossClient是否存在
        if(!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        //构建文件路径
        String folder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileClass = originalFileName.substring(originalFileName.lastIndexOf("."));
        String key = module+"/"+folder+"/"+fileName+fileClass;
        //文件上传流
        ossClient.putObject(bucketname,key,inputStream);
        ossClient.shutdown();
        String resUrl = "https://"+bucketname+"."+endpoint+"/"+key;
        return resUrl ;
    }

    @Override
    public void removeFile(String url) {
        String endpoint = ossPropertity.getEndpoint();
        String bucketname = ossPropertity.getBucketname();
        String keyid = ossPropertity.getKeyid();
        String keysecret = ossPropertity.getKeysecret();
        OSS ossClient = new OSSClientBuilder().build(endpoint,keyid,keysecret);
        //判断ossClient是否存在
        if(!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        String resUrl = "https://"+bucketname+"."+endpoint+"/";
        String objectName = url.substring(resUrl.length());
        ossClient.deleteObject(bucketname,objectName);
        ossClient.shutdown();
    }
}

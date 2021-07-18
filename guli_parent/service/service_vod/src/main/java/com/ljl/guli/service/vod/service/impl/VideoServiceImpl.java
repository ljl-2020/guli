/**
 * FileName: VideoServiceImpl
 * Author: ljl
 * Date: 2021/6/16 12:19
 * Description:
 * History:
 */


package com.ljl.guli.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.ljl.guli.common.base.result.ResultCode;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.vod.service.VideoService;
import com.ljl.guli.service.vod.utils.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

import static com.ljl.guli.service.vod.utils.AliyunVodSDKUtil.initVodClient;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;
    @Override
    public String uploadVideo(InputStream inputStream, String originalFileName) {
        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title,
                originalFileName,
                inputStream);
        request.setTemplateGroupId(vodProperties.getTemplateGroupId());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        if("".equals(videoId) || null == videoId){
            log.error("阿里云上传错误"+response.getCode()+"---"+response.getMessage());
            throw new MyException(ResultCode.VIDEO_UPLOAD_ERROR);
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {
        DefaultAcsClient client = initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        DeleteVideoResponse response = client.getAcsResponse(request);

    }

    @Override
    public void removeVideoByIdList(List<String> videoIdList) throws ClientException {
        DefaultAcsClient client = initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < videoIdList.size(); i++ ){
            stringBuffer.append(videoIdList.get(i));
            if(i== (videoIdList.size()-1) || (i+1)%20 == 0){
                //删除
                log.info("删除id列表为："+stringBuffer.toString());
                request.setVideoIds(stringBuffer.toString());
                DeleteVideoResponse response = client.getAcsResponse(request);
                //清空
                stringBuffer = new StringBuffer();
            }else {
                stringBuffer.append(",");
            }

        }

    }

    @Override
    public String getPlayAuth(String sourceId) throws ClientException {
        DefaultAcsClient client = initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(sourceId);
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        return response.getPlayAuth();
    }


}

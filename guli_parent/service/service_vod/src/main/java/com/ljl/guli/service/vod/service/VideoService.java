package com.ljl.guli.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

public interface VideoService {

    String uploadVideo(InputStream inputStream,String originalFileName);

    void removeVideo(String videoId) throws ClientException;

    void removeVideoByIdList(List<String> videoIdList) throws ClientException;

    String getPlayAuth(String sourceId) throws ClientException;
}

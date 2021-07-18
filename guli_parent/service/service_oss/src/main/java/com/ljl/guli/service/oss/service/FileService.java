/**
 * FileName: FileService
 * Author: ljl
 * Date: 2021/5/15 11:39
 * Description: 文件上传
 * History:
 */


package com.ljl.guli.service.oss.service;

import java.io.InputStream;

public interface FileService {


        /**
         * 功能描述: aliyumOss文件上传
         * @param: inputStream 输入流
         * @param: module 文件模型（头像文件，excel文件等）
         * @param: originalFileName 文件原始名称
         * @return: 文件在oss服务器的url地址
         * @since: 1.0.0
         * @Author: ljl
         * @Date: 2021/5/15 11:41
         */

    String upload(InputStream inputStream, String module,String originalFileName);

    void removeFile(String url);
}

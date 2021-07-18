/**
 * FileName: ResultCode
 * Author: ljl
 * Date: 2021/3/20 20:19
 * Description:
 * History:
 */


package com.ljl.guli.common.base.result;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  ResultCode {

     SUCCESS(true,"成功",20000),

     UN_KNOW_ERROR(false,"未知错误",20001),

     BAD_SQL_ERROR(false,"sql语法错误",20010),

     OSS_UPLOAD_ERROR(false,"文件上传错误",50001),

    VIDEO_UPLOAD_ERROR(false,"视频上传错误",60001),

    VIDEO_DELETE_ERROR(false,"视频删除错误",60002),
    VIDEO_GET_ERROR(false,"视频凭证获取失败",60003),

    EXCEL_DATA_ERROR(false,"Excel文件导入错误",20021);


    private Boolean success;
    private String message;
    private Integer code;

    ResultCode(Boolean success,String message,Integer code){
        this.code=code;
        this.message=message;
        this.success=success;
    }

}

/**
 * FileName: MyException
 * Author: ljl
 * Date: 2021/5/15 13:07
 * Description:
 * History:
 */


package com.ljl.guli.service.base.exception;


import com.ljl.guli.common.base.result.ResultCode;
import lombok.Data;

@Data
public class MyException extends RuntimeException {

    private Integer code ;

    public MyException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MyException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
}

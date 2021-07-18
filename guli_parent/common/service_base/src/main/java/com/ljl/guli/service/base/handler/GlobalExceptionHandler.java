/**
 * FileName: GlobalExceptionHandler
 * Author: ljl
 * Date: 2021/3/23 21:22
 * Description:
 * History:
 */


package com.ljl.guli.service.base.handler;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.service.base.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("未知异常");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e){
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}

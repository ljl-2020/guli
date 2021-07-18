/**
 * FileName: ResponseUtil
 * Author: ljl
 * Date: 2021/7/17 17:22
 * Description:
 * History:
 */


package com.ljl.guli.common.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljl.guli.common.base.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getOutputStream(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


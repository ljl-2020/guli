/**
 * FileName: UnauthorizedEntryPoint
 * Author: ljl
 * Date: 2021/7/17 17:07
 * Description:
 * History:
 */


package com.ljl.guli.security.security;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil.out(response, R.error());
    }
}


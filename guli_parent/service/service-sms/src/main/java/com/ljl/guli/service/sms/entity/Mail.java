/**
 * FileName: Mail
 * Author: ljl
 * Date: 2021/7/13 11:35
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String toPeo;
    private String fromPeo;
    private static final String TITLE = "验证码";
}

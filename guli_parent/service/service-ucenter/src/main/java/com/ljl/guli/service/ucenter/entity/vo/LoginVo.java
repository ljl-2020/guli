/**
 * FileName: LoginVo
 * Author: ljl
 * Date: 2021/7/13 15:32
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {

    private String mobile;

    private String password;
}

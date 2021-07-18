/**
 * FileName: RegisterVo
 * Author: ljl
 * Date: 2021/7/13 13:06
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {

    private String nickName;
    private String mobile;
    private String mail;
    private String password;
    private String code;
}

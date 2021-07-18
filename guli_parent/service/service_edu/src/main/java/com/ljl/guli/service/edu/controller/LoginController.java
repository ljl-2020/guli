/**
 * FileName: LoginController
 * Author: ljl
 * Date: 2021/3/25 21:37
 * Description: 登录模拟
 * History:
 */


package com.ljl.guli.service.edu.controller;

import com.ljl.guli.common.base.result.R;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin //跨域
@RestController
@RequestMapping("/vue-admin-template/user")
public class LoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin-token");
    }

    @GetMapping("info")
    public R info(){
        return R.ok()
                .data("name","admin")
                .data("roles","[admin]")
                .data("avatar","https://i0.hdslb.com/bfs/face/member/noface.jpg@140w_140h_1c.jpg");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();

    }
}

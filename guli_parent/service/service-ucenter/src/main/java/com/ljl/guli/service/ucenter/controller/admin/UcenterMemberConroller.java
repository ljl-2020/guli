/**
 * FileName: UcenterMemberConroller
 * Author: ljl
 * Date: 2021/7/16 18:20
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.controller.admin;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/ucenter/member")
//@CrossOrigin
@Api(description = "会员管理")
public class UcenterMemberConroller {

    @Autowired
    private MemberService memberService;

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
}

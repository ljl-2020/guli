package com.ljl.guli.service.ucenter.controller.api;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.JwtInfo;
import com.ljl.guli.common.base.utils.JwtUtil;
import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.ucenter.entity.Member;
import com.ljl.guli.service.ucenter.entity.vo.LoginVo;
import com.ljl.guli.service.ucenter.entity.vo.RegisterVo;
import com.ljl.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ljl
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/api/ucenter/member")
//@CrossOrigin
@Api(description = "会员管理")
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok().message("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token  = memberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        JwtInfo member1 = JwtUtil.getMemberByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(member1.getId());
        return R.ok().data("userInfo",member);
    }
    //根据用户id获取用户信息
    @ApiOperation("根据memberID获取用户信息")
    @GetMapping("inner/get-member-dto/{id}")
    public MemberDto getUserInfoOrder(@ApiParam(value = "memberId",required = true)
            @PathVariable String id) {
       MemberDto memberDto = memberService.getMemberDtoByMemberID(id);
        return memberDto;
    }



}


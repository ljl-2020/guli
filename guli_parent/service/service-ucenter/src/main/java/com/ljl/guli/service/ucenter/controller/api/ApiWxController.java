/**
 * FileName: ApiWxController
 * Author: ljl
 * Date: 2021/7/14 11:56
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.controller.api;

import com.google.gson.Gson;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.common.base.utils.JwtInfo;
import com.ljl.guli.common.base.utils.JwtUtil;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.ucenter.entity.Member;
import com.ljl.guli.service.ucenter.service.MemberService;
import com.ljl.guli.service.ucenter.utils.HttpClientsUtil;
import com.ljl.guli.service.ucenter.utils.UcenterProperties;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/api/ucenter/wx")
//@CrossOrigin
@Api(description = "微信登录")
@Slf4j
public class ApiWxController {

    @Autowired
    private UcenterProperties ucenterProperties;

    @Autowired
    private MemberService memberService;


    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ucenterProperties.getAppId(),
                    ucenterProperties.getAppSecret(),
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientsUtil.get(accessTokenUrl);

            //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");

            //把扫描人信息添加数据库里面
            //判断数据表里面是否存在相同微信信息，根据openid判断
            Member member = memberService.getOpenIdMember(openid);
            if(member == null) {//memeber是空，表没有相同微信数据，进行添加

                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientsUtil.get(userInfoUrl);
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String avatar = (String)userInfoMap.get("headimgurl");//头像

                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(avatar);
                memberService.save(member);
            }
            JwtInfo jwtInfo = new JwtInfo();
            jwtInfo.setNickName(member.getNickname());
            jwtInfo.setAvatar(member.getAvatar());
            jwtInfo.setId(member.getId());

            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtil.getJwtToken(jwtInfo);
            //最后：返回首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;
        }catch(Exception e) {
            throw new MyException("登录失败",20001);
        }
    }


    @GetMapping("login")
   public String genQrConect(HttpSession session){

        //组装url地址
        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String encode ="";
        try {
             encode = URLEncoder.encode(ucenterProperties.getRedirectUri(), "UTF-8");
        }catch (UnsupportedEncodingException e){
            log.error(ExceptionUtil.getMessage(e));
            throw  new MyException("url转换出错",20012);
        }
        //生成随机State
        String state = UUID.randomUUID().toString();
        session.setAttribute("wx_open_state",state);
        String qrCodeUrl = String.format(baseUrl,ucenterProperties.getAppId(),encode,state);
        //跳转到url地址
       return "redirect:"+qrCodeUrl;
    }
}

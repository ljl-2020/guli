/**
 * FileName: ApiStmpController
 * Author: ljl
 * Date: 2021/7/13 11:43
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.controller;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.RandomUtil;
import com.ljl.guli.service.sms.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/stmp")
//@CrossOrigin
@Api(description = "邮箱Api")
public class ApiStmpController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MailService mailService;

    @GetMapping("send/{toPer}")
    @ApiOperation("发送短信")
    public R sendMail(
            @ApiParam(value = "发送邮箱",required = true) @PathVariable String toPer) {
//        String code = redisTemplate.opsForValue().get(toPer);
//        if(!StringUtils.isEmpty(code)) {
//            return R.ok();
//        }
        String code = RandomUtil.getSixBitRandom();
        String title = "注册验证码";
        String content = "您的验证码为："+code+" ,五分钟内有效，请尽快完成注册【注意】本邮件请勿转发或回复";
        boolean b = mailService.sendSimpleMail(toPer, title, content);
        if(b) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(toPer,code,5, TimeUnit.MINUTES);
            return R.ok().message("发送成功，请查收");
        } else {
            return R.error().message("发送失败");
        }

    }
}

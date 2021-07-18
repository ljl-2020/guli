/**
 * FileName: ApiSmsController
 * Author: ljl
 * Date: 2021/7/13 10:33
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.controller;



import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.RandomUtil;
import com.ljl.guli.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/smservice/sms")
//@CrossOrigin
@Api(description = "短信Api")
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    @ApiOperation("发送短信")
    public R sendMsm(
            @ApiParam(value = "电话号码",required = true) @PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = smsService.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}

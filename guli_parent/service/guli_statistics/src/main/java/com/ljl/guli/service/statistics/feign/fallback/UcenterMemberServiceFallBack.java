/**
 * FileName: UcenterMemberServiceFallBack
 * Author: ljl
 * Date: 2021/7/17 11:19
 * Description:
 * History:
 */


package com.ljl.guli.service.statistics.feign.fallback;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public R countRegister(String day) {
        log.error("熔断保护");
        return R.ok().data("countRegister",0);
    }
}

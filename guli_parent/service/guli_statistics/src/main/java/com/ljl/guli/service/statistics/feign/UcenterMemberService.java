package com.ljl.guli.service.statistics.feign;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.statistics.feign.fallback.UcenterMemberServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBack.class)
@Service
public interface UcenterMemberService {

    @GetMapping("/admin/ucenter/member/countRegister/{day}")
    R countRegister(@PathVariable String day) ;
}

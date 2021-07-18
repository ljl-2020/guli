package com.ljl.guli.service.trade.feign;

import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.trade.feign.fallback.UcenterMemberServiceFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "service-ucenter",fallback = UcenterMemberServiceFallback.class)
public interface UcenterMemberService {
    @ApiOperation("根据memberID获取用户信息")
    @GetMapping("/api/ucenter/member/inner/get-member-dto/{id}")
    public MemberDto getUserInfoOrder(@ApiParam(value = "memberId",required = true)
                                      @PathVariable String id) ;
}

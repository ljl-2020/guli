/**
 * FileName: UcenterMemberServiceFallback
 * Author: ljl
 * Date: 2021/7/14 15:25
 * Description:
 * History:
 */


package com.ljl.guli.service.trade.feign.fallback;

import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.trade.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UcenterMemberServiceFallback implements UcenterMemberService {
    @Override
    public MemberDto getUserInfoOrder(String id) {
        log.info("熔断保护");
        return null;
    }
}

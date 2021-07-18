package com.ljl.guli.service.cms.feign.fallback;

import com.ljl.guli.service.cms.feign.OssFileService;
import com.ljl.guli.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R removeFile(String url) {
        log.warn("熔断保护");
        return R.error().message("调用超时");
    }
}

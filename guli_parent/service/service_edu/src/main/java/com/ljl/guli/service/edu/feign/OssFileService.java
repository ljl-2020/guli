package com.ljl.guli.service.edu.feign;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.feign.fallback.OssFFilesServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-oss",fallback = OssFFilesServiceFallBack.class)
@Service
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
   R removeFile(@RequestBody String url);
    @GetMapping("/admin/oss/file/concurrent")
    R test();
}

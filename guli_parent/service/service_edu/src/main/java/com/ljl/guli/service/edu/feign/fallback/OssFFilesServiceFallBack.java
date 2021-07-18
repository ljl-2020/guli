/**
 * FileName: OssFFilesServiceFallBack
 * Author: ljl
 * Date: 2021/6/2 14:14
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.feign.fallback;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OssFFilesServiceFallBack implements OssFileService {
    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }

    @Override
    public R test() {
        return R.error();
    }
}

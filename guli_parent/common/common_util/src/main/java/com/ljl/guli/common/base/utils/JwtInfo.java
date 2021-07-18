/**
 * FileName: JwtInfo
 * Author: ljl
 * Date: 2021/7/13 15:29
 * Description:
 * History:
 */


package com.ljl.guli.common.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInfo {

    private String id;
    private String avatar;
    private String nickName;
}

/**
 * FileName: OrderNoUtils
 * Author: ljl
 * Date: 2021/7/14 14:44
 * Description:
 * History:
 */


package com.ljl.guli.service.trade.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单号工具类
 *
 * @author ljl
 * @since 1.0
 */
public class OrderNoUtil {

    /**
     * 获取订单号
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }

}


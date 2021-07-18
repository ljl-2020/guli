/**
 * FileName: ApiOrderController
 * Author: ljl
 * Date: 2021/7/14 15:15
 * Description:
 * History:
 */


package com.ljl.guli.service.trade.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.JwtInfo;
import com.ljl.guli.common.base.utils.JwtUtil;
import com.ljl.guli.service.trade.entity.TOrder;
import com.ljl.guli.service.trade.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/trade/order")
//@CrossOrigin
@Api("订单管理")
@Slf4j
public class ApiOrderController {

    @Autowired
    private TOrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("auth/save/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request){
        String header = request.getHeader("token");
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        String orderId = orderService.saveOrder(courseId,jwtInfo.getId());
        return R.ok().data("orderId",orderId);
    }

    //2 根据订单id查询订单信息
    @GetMapping("auth/get/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("id",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item",order);
    }
    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("auth/is-buy/{courseId}")
    public R isBuyCourse(@PathVariable String courseId,HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        boolean b = orderService.isBuy(courseId,jwtInfo.getId());
        return R.ok().data("isBuy",b);
    }

    //根据订单号查询订单表中订单状态
    @GetMapping("query-pay-status/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        TOrder order = orderService.getOrderByOdrderNo(orderNo);
        if(order != null){
            return R.ok().message("支付成功");
        }
        return R.ok().message("支付中");
    }

    //根据用户id查询订单
    @GetMapping("auth/list")
    public R list(HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        List<TOrder> orderList = orderService.selectByMemberId(jwtInfo.getId());
        return R.ok().data("orderList",orderList);
    }

    //根据订单id删除订单
    @DeleteMapping("auth/remove/{orderId}")
    public R removeById(@PathVariable String orderId,HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        boolean b = orderService.removeById(orderId,jwtInfo.getId());
        if(b){
            return R.ok().message("删除成功");
        }else {
            return R.ok().message("删除失败");
        }

    }


}

package com.ljl.guli.service.trade.service;

import com.ljl.guli.service.trade.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author ljl
 * @since 2021-07-14
 */
public interface TOrderService extends IService<TOrder> {

    String saveOrder(String courseId, String memberId);

    boolean isBuy(String courseId,String memberId);

    TOrder getOrderByOdrderNo(String orderNo);

    List<TOrder> selectByMemberId(String id);

    boolean removeById(String orderId, String id);

    void updateOrderStatus(Map<String, String> map);

    TOrder getOrderByOdrderNoAndMemberId(String orderNo, String id);
}

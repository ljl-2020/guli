package com.ljl.guli.service.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.trade.entity.TOrder;
import com.ljl.guli.service.trade.entity.TPayLog;
import com.ljl.guli.service.trade.feign.EduCourseService;
import com.ljl.guli.service.trade.feign.UcenterMemberService;
import com.ljl.guli.service.trade.mapper.TOrderMapper;
import com.ljl.guli.service.trade.mapper.TPayLogMapper;
import com.ljl.guli.service.trade.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljl.guli.service.trade.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ljl
 * @since 2021-07-14
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Autowired
    private TPayLogMapper payLogMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(Map<String, String> map) {

        //更新订单状态
        String orderNo = map.get("out_trade_no");
        TOrder order = this.getOrderByOdrderNo(orderNo);
        order.setStatus(1);//支付成功
        baseMapper.updateById(order);

        // 开始 ：模拟内容
        map.put("total_fee",order.getTotalFee().toString());
        map.put("result_code","SUCCESS");
        map.put("transaction_id", UUID.randomUUID().toString());
        //结束：以上为模拟回调后的结果，可注释
        //更新购买数量
        eduCourseService.updateCourseById(order.getCourseId());
        //记录支付日志
        TPayLog payLog = new TPayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(new BigDecimal(map.get("total_fee")));//总金额(分)
        payLog.setTradeState(map.get("result_code"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(new Gson().toJson(map));
        payLogMapper.insert(payLog);
    }

    @Override
    public TOrder getOrderByOdrderNoAndMemberId(String orderNo, String id) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        queryWrapper.eq("member_id",id);
        return baseMapper.selectOne(queryWrapper);
    }


    @Override
    public String saveOrder(String courseId, String memberId) {
        //查询当前用户是否已有当前课程的订单
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        TOrder order1 = baseMapper.selectOne(queryWrapper);
        if(order1!= null ){
            return order1.getId();
        }else {
            //查询课程信息
            CourseDto courseDto = eduCourseService.getCourseDtoById(courseId);
            if(courseDto == null){
                throw new MyException("参数错误",20011);
            }
            MemberDto memberDto = ucenterMemberService.getUserInfoOrder(memberId);
            if(memberDto == null){
                throw new MyException("参数错误",20011);
            }
            TOrder order = new TOrder();
            order.setOrderNo(OrderNoUtil.getOrderNo());
            order.setCourseCover(courseDto.getCover());
            order.setCourseId(courseId);
            order.setCourseTitle(courseDto.getTitle());
            order.setTeacherName(courseDto.getTeacherName());
            order.setTotalFee(courseDto.getPrice().multiply(new BigDecimal(100)));
            order.setMemberId(memberDto.getId());
            order.setMobile(memberDto.getMobile());
            order.setNickname(memberDto.getNickname());
            order.setStatus(0);
            order.setPayType(1);
            baseMapper.insert(order);
            return order.getId();
        }
    }

    @Override
    public boolean isBuy(String courseId,String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0) { //已经支付
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TOrder getOrderByOdrderNo(String orderNo) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder order = baseMapper.selectOne(queryWrapper);
        return order;
    }

    @Override
    public List<TOrder> selectByMemberId(String id) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("member_id",id);
        List<TOrder> orderList = baseMapper.selectList(queryWrapper);
        return orderList;
    }

    @Override
    public boolean removeById(String orderId, String id) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        queryWrapper.eq("member_id",id);
        return this.remove(queryWrapper);
    }
}

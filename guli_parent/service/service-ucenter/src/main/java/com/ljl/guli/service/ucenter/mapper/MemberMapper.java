package com.ljl.guli.service.ucenter.mapper;

import com.ljl.guli.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author ljl
 * @since 2021-07-13
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterDay(String day);

}

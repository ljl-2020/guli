package com.ljl.guli.service.ucenter.service;

import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.ucenter.entity.vo.LoginVo;
import com.ljl.guli.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ljl
 * @since 2021-07-13
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Integer countRegisterDay(String day);

    Member getOpenIdMember(String openid);

    MemberDto getMemberDtoByMemberID(String id);
}

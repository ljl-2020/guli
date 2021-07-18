package com.ljl.guli.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ljl.guli.common.base.utils.JwtInfo;
import com.ljl.guli.common.base.utils.JwtUtil;
import com.ljl.guli.common.base.utils.MD5;
import com.ljl.guli.service.base.dto.MemberDto;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.ucenter.entity.Member;
import com.ljl.guli.service.ucenter.entity.vo.LoginVo;
import com.ljl.guli.service.ucenter.entity.vo.RegisterVo;
import com.ljl.guli.service.ucenter.mapper.MemberMapper;
import com.ljl.guli.service.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ljl
 * @since 2021-07-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickName(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new MyException("注册失败",20002);
        }
        //判断验证码
        //获取redis验证码
        Object o = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(o.toString())) {
            throw new MyException("验证码错误",20003);
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new MyException("手机号已被注册",20004);
        }

        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setIsDisabled(false);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png");
        baseMapper.insert(member);


    }

    @Override
    public String login(LoginVo loginVo) {
        //校验：输入参数是否合法

        String mobile = loginVo.getMobile(); //手机号
        String password = loginVo.getPassword(); //密码
        if(StringUtils.isEmpty(mobile) ||StringUtils.isEmpty(password)){
            throw new MyException("输入参数为空",20005);
        }
        //校验邮箱是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new MyException("邮箱号不正确",20006);
        }
        //校验密码是否正确
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new MyException("登陆密码错误",20007);
        }
        //校验用户是否禁用
        if(member.getIsDeleted()){
            throw new MyException("用户被禁用",20008);
        }
        //登录：生成Token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setAvatar(member.getAvatar());
        jwtInfo.setNickName(member.getNickname());
        String jwtToken = JwtUtil.getJwtToken(jwtInfo);
        return jwtToken;
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public MemberDto getMemberDtoByMemberID(String id) {
        Member member = baseMapper.selectById(id);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }

    //查询某一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }

}

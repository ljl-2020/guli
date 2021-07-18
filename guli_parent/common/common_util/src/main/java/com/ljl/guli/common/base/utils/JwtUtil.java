/**
 * FileName: JwtUtil
 * Author: ljl
 * Date: 2021/7/13 15:26
 * Description:
 * History:
 */


package com.ljl.guli.common.base.utils;

import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtUtil {

        //常量
        public static final long EXPIRE = 1000 * 60 * 60 * 24; //token过期时间
        public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥

        //生成token字符串的方法
        public static String getJwtToken(JwtInfo jwtInfo){
            String id = jwtInfo.getId();
            String avatar = jwtInfo.getAvatar();
            String nickname = jwtInfo.getNickName();
            if(StringUtils.isEmpty(id) ||StringUtils.isEmpty(avatar) ||StringUtils.isEmpty(nickname) ){
                throw new RuntimeException("输入参数错误");
            }
            String JwtToken = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    .setSubject("guli-user")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                    .claim("id", id)  //设置token主体部分 ，存储用户信息
                    .claim("nickname", nickname)
                    .claim("avatar",avatar)
                    .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                    .compact();

            return JwtToken;
        }

        /**
         * 判断token是否存在与有效
         * @param jwtToken
         * @return
         */
        public static boolean checkToken(String jwtToken) {
            if(StringUtils.isEmpty(jwtToken)) return false;
            try {
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        /**
         * 判断token是否存在与有效
         * @param request
         * @return
         */
        public static boolean checkToken(HttpServletRequest request) {
            try {
                String jwtToken = request.getHeader("token");
                if(StringUtils.isEmpty(jwtToken)) return false;
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        /**
         * 根据token字符串获取会员id
         * @param request
         * @return
         */
        public static String getMemberIdByJwtToken(HttpServletRequest request) {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String)claims.get("id");
        }

    /**
     * 根据token字符串获取会员信息
     * @param request
     * @return
     */
    public static JwtInfo getMemberByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        JwtInfo jwtInfo = new JwtInfo(claims.get("id").toString(), claims.get("nickname").toString(), claims.get("avatar").toString());
        return jwtInfo;
    }

}

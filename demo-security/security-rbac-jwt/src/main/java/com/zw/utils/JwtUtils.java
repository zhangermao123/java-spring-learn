package com.zw.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zw.contant.ParamKey;
import com.zw.contant.Status;
import com.zw.entity.property.JwtProperty;
import com.zw.entity.vo.UserPrincipal;
import com.zw.exception.SecurityException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.utils
 * @descripation TODO
 * @date 2021-06-15
 */
@Slf4j
@Component
public class JwtUtils {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JwtProperty jwtProperty;

    /**
     * @description 创建jwt
     * @param id            用户id
     * @param username      用户名
     * @param roles         用户角色
     * @param authorities   用户权限
     * @param rememberMe    是否开启rememberMe true 开启
     * @return jwt String
     */
    public String createJWT(Long id, String username, List<String> roles, Collection<? extends GrantedAuthority> authorities, boolean rememberMe){
        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder()
                                .setId(id.toString()) //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                                .setSubject(username) //用户名
                                .setIssuedAt(now) //iat: jwt的签发时间
                                .claim(ParamKey.JWT_CHAIM_ROLES,roles) //添加的自定义chaim -角色列表
                                .claim(ParamKey.JWT_CHAIM_AUTH,authorities) //关系列表
                                .signWith(SignatureAlgorithm.HS256, jwtProperty.getKey());
        // 设置过期时间
        Long ttl = rememberMe ? jwtProperty.getRemember() : jwtProperty.getTtl();
        if (ttl > 0) {
            jwtBuilder.setExpiration(DateUtil.offsetMillisecond(now,ttl.intValue()));
        }
        String jwt = jwtBuilder.compact();//创建jwt
        redisUtils.set(username,jwt,ttl); //set redis
        return jwt;
    }

    /**
     * @description 创建jwt
     * @param authentication 用户认证信息
     * @param rememberMe     记住我
     * @return java.lang.String
     */
    public String createJWT(Authentication authentication, Boolean rememberMe) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(userPrincipal.getId(),userPrincipal.getUsername(),userPrincipal.getRoles(),userPrincipal.getAuthorities(),rememberMe);
    }

    /**
     * 解析JWT
     *
     * @param jwt JWT
     * @return {@link Claims}
     */
    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtProperty.getKey()).parseClaimsJws(jwt).getBody();

            String username = claims.getSubject();


            // 校验redis中的JWT是否存在
            if (redisUtils.ifNull(username)) {
                throw new SecurityException(Status.TOKEN_EXPIRED);
            }

            // 校验redis中的JWT是否与当前的一致，不一致则代表用户已注销/用户在不同设备登录，均代表JWT已过期
            if (!StrUtil.equals(jwt,redisUtils.get(username))) {
                throw new SecurityException(Status.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new SecurityException(Status.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * 根据 jwt 获取用户名
     *
     * @param jwt JWT
     * @return 用户名
     */
    public String getUsernameFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    /**
     * 设置JWT过期,清除redis中缓存
     *
     * @param request 请求
     */
    public void invalidateJWT(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        // 从redis中清除JWT
        redisUtils.delete(username);
    }


}

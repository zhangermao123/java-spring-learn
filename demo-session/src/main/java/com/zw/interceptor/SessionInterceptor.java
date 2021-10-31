package com.zw.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.zw.contants.Const;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.interceptor
 * @descripation TODO
 * @date 2021-06-18
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        //如果session有值
        if(ObjectUtil.isNotNull(httpSession.getAttribute(Const.SESSION_KEY))){
            //有权限继续执行
            return true;
        }
        // 跳转到登录页
        String url = "/page/login?redirect=true";
        response.sendRedirect(request.getContextPath()+url);
        //后续不执行
        return false;
    }
}

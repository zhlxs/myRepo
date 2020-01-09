package com.data.masterandslave.interceptor;

import com.data.masterandslave.controller.LoginController;
import com.data.masterandslave.entity.QuanXianBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    // 在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader("token");
        if (null == token || !(token instanceof String)) {
            httpServletResponse.setStatus(403,"权限不足");
            return false;
        }

        QuanXianBean quanXianBean = LoginController.tokenMap.get(token);
        if (null == quanXianBean) {
            httpServletResponse.setStatus(403,"权限不足");
            return false;
        }

        if (System.currentTimeMillis() - quanXianBean.getTimevalue() > LoginController.expireTime) {
            // token超时
            httpServletResponse.setStatus(403,"权限不足");
            return false;
        }else {
            // 没有超时，则更新过期时间
            quanXianBean.setTimevalue(System.currentTimeMillis());
        }
        // 校验是否又权限
        String resource = httpServletRequest.getRequestURI() + "_" + httpServletRequest.getMethod();
        if (!quanXianBean.getResources().contains(resource)) {
            httpServletResponse.setStatus(403,"权限不足");
            return false;
        }
        return true;
    }

    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
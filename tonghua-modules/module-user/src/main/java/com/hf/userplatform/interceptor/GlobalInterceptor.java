package com.hf.userplatform.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;
import com.hf.core.utils.JwtUtil;
import com.hf.userplatform.utils.TokenHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在请求处理之前执行，可以访问请求头参数
        String authorization = request.getHeader("Authorization");
        if (!StrUtil.hasBlank(authorization)) {
            if (!JwtUtil.verifyToken(authorization)) {
                throw new RuntimeException();
            }
//            String id = JwtUtil.parseJwt(authorization).getId();
            String id = JwtUtil.getTokenInfo(authorization).getId();
            TokenHolder.save(id);
        }
        // 执行你的逻辑处理
        return true; // 返回true表示继续处理请求，返回false表示中止请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 在请求处理之后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // 在请求完成后执行
        TokenHolder.remove();
    }
}

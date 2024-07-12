// FILEPATH: /cloudide/workspace/Java/src/main/java/com/vie/space/interceptor/SessionInterceptor.java
package com.vie.space.core.interceptor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.vie.space.core.context.UserContext;
import com.vie.space.core.context.UserContextHolder;
import com.vie.space.entity.BaseUser;
import com.vie.space.service.BaseUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private BaseUserService baseUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("SessionInterceptor - 拦截到请求");

        String sessionId = getSessionCookie(request);
        if (sessionId != null) {
            UserContext cachedUser = UserContextHolder.getUserContext();
            String cachedSessionid = cachedUser != null ? cachedUser.getSession() : null;
            if (sessionId.equals(cachedSessionid)) {
                return true;
            } else {
                // 如果后端重启，缓存sessionid会被重置，需要从数据库中查找sessionid是否存在
                BaseUser user = baseUserService.selectBySession(sessionId);
                if (user != null) {
                    cachedSessionid = user.getSession();
                    if (sessionId.equals(cachedSessionid)) {
                        UserContext userContext = new UserContext(user.getId(), user.getUsername(), user.getSession());
                        UserContextHolder.setUserContext(userContext);
                        return true;
                    }
                }
            }
        }

        log.error("未找到有效的sessionid cookie");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;
    }

    private String getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "=" + cookie.getValue());
                if (cookie.getName().equals("sessionid")) {
                    String sessionId = cookie.getValue();
                    return sessionId;
                }
            }
        }
        return null;
    }

}
// FILEPATH: /cloudide/workspace/Java/src/main/java/com/vie/space/controller/UserController.java
package com.vie.space.controller;

import com.vie.space.core.context.UserContext;
import com.vie.space.core.context.UserContextHolder;
import com.vie.space.entity.BaseUser;
import com.vie.space.service.BaseUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BaseUserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody BaseUser userParams,
            HttpServletResponse response) {
        String username = userParams.getUsername();
        String password = userParams.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseEntity.badRequest().build();
        }

        BaseUser user = userService.selectByUsernameAndPassword(username, password);
        if (user != null) {
            // 生成session并放到cookie中
            String sessionid = generateSessionId();
            addSessionCookie(response, sessionid);
            // 缓存/存储用户session
            user.setSession(sessionid);
            setAuthentication(user);
            userService.updateSessionById(user);
            // 返回用户信息
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("username", user.getUsername());
            userInfo.put("session", sessionid);
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    /**
     * 生成一个随机的会话标识符
     * 使用 UUID 类生成一个随机的 UUID（通用唯一标识符），并将其转换为字符串形式
     * UUID 是一个 128 位的数字，通常表示为 32 个十六进制数字的字符串，具有高度的随机性和唯一性
     * 返回生成的会话标识符
     *
     * @return 一个随机的 UUID 字符串，用作会话标识符
     */
    private String generateSessionId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将sessionId作为HttpOnly Cookie添加到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param sessionId   需要被添加为Cookie的字符串
     */
    private void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = new Cookie("sessionid", sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    /**
     * 缓存用户的认证信息
     */
    private void setAuthentication(BaseUser user) {
        UserContextHolder.setUserContext(new UserContext(user.getId(), user.getUsername(), user.getSession()));
    }
}
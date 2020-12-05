package com.xlh.cors.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年12月04日 23:08 胡晓磊 Exp $
 */
@Controller
@Slf4j
public class CorsBController {

    @GetMapping(value = "/b")
    public String indexB(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "testB");
        cookie.setPath("/");
        cookie.setDomain("b.com");
        response.addCookie(cookie);
        return "b";
    }

    @PostMapping(value = "/getB")
    @ResponseBody
    public ResponseEntity<Cookie[]> getA(String username, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            log.info("domain {}, name {}, value {}", cookie.getDomain(), cookie.getName(), cookie.getValue());
        }
        return ResponseEntity.ok(cookies);
    }
}

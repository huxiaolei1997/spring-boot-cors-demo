package com.xlh.cors.controller;

import com.xlh.cors.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2020年12月04日 23:08 胡晓磊 Exp $
 */
@Controller
@Slf4j
public class CorsAController {

    @GetMapping(value = "/a")
    public String indexA(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("usernameA", "testA")
                .httpOnly(false) // 是否禁止js读取，true-是，false-否
                .secure(true)  // true代表cookie在https下传输，在http下不传输
                .domain("a.com").path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite(SameSiteCookies.NONE.getValue())
                .build();

        /**
         * 如果sameSite是NONE，那么secure必须是true，即cookie只能在https下传输，如果设置了false，在微软新版Edge下会提示
         * This Set-Cookie was blocked because it had the "SameSite=None" attribute but did not have the "Secure" attribute,
         * which is required in order to use "SameSite=None"
         * 在chrome下也会提示类似的
         */

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//        Cookie cookie = new Cookie("usernameA", "testA");
//        cookie.setPath("/");
//        cookie.setDomain("a.com");
////        cookie.set
//        response.addCookie(cookie);
        return "a";
    }


    @PostMapping(value = "/getA")
    @ResponseBody
    public ResponseEntity<Cookie[]> getA(String username, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            log.info("domain {}, name {}, value {}", cookie.getDomain(), cookie.getName(), cookie.getValue());
        }
        return ResponseEntity.ok(cookies);
    }


    @PostMapping(value = "/getAForRequestJson")
    @ResponseBody
//    public ResponseEntity<Cookie[]> getA(@RequestBody User user, HttpServletRequest request) {
    public ResponseEntity<User> getA(@RequestBody User user, HttpServletRequest request) {
        log.info("User {}", user);
//        Cookie[] cookies = request.getCookies();

//        for (Cookie cookie : cookies) {
//            log.info("domain {}, name {}, value {}", cookie.getDomain(), cookie.getName(), cookie.getValue());
//        }
//        return ResponseEntity.ok(cookies);
        return ResponseEntity.ok(user);
    }
}

package com.ditto.cookiez.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Zhihao Liang
 * @date 2020/9/23 0:43
 * @email s3798366@student.rmit.edu.au
 */
public class WebUtil {
    public static Cookie getCookieVal(HttpServletRequest request,
                                      String name) {
        //1.将cookies放到map中去
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        //2.查找是否存在cookie,是则返回查找到的cookie
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }
    public static void setCookieVal(HttpServletResponse response,
                                    String name,
                                    String value,
                                    int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}

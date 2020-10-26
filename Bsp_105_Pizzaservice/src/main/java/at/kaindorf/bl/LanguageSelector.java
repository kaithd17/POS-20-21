/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kainz
 */
public class LanguageSelector {

    public static Language getLanguage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("lang")) {
                    return Language.valueOf(cooky.getValue());
                }
            }
        } catch (NullPointerException ex) {

        }
        Cookie cookie = new Cookie("lang",Language.DE.getLanguageCode());
        response.addCookie(cookie);
        return Language.DE;
    }
}

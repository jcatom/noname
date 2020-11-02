package cc.jml1024.subdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Evil
 */
@Controller
public class VerifyCodeController {

    @RequestMapping("/verifyCode/image")
    public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) {

        return "";
    }

}

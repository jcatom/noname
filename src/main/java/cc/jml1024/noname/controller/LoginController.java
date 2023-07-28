package cc.jml1024.noname.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Evil
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        logger.info("SPRING_SECURITY_LAST_EXCEPTION:{}", obj);
        return "login";
    }
}

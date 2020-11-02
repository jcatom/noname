package cc.jml1024.subdragon.controller;

import cc.jml1024.subdragon.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Evil
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/login")
    public String login() {
        logger.info("=========Login request========");
        return "login";
    }

    @PostMapping("/loginAuth")
    public String loginAuth(LoginDTO dto) {
        logger.info("login dto : [{}]", dto);
        return "redirect:/controlPanel";
    }
}

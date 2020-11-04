package cc.jml1024.subdragon.controller;

import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Evil
 */
@Controller
public class VerifyCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Producer producer;

    @RequestMapping("/verifyCode/image")
    public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        logger.info("test: [{}]", producer.createText());
        return "";
    }

}

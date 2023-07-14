package cc.jml1024.subdragon.controller;


import cc.jml1024.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Evil
 */
@Controller
public class VerifyCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String HOME_CAPTCHA_SESSION_KEY = "homeCaptcha";

    private final static String HOME_CAPTCHA_SESSION_DATE = "homeCaptchaDate";

//    @Autowired
    private Producer producer;

    @Autowired
    @Qualifier("kaptchaProps")
    private Properties properties;

    @RequestMapping("/verifyCode/image")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache");

        HttpSession session = request.getSession();

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;


        // write the data out
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            logger.error("Failed to write image to output stream", e);
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // fixes issue #69: set the attributes after we write the image in case the image writing fails.

        // store the text in the session
//        session.setAttribute(HOME_CAPTCHA_SESSION_KEY, capText);

        // store the date in the session so that it can be compared
        // against to make sure someone hasn't taken too long to enter
        // their kaptcha
//        session.setAttribute(HOME_CAPTCHA_SESSION_DATE, new Date());
    }

}

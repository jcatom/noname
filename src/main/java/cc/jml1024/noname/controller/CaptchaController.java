package cc.jml1024.noname.controller;


import cc.jml1024.kaptcha.Producer;
import cc.jml1024.spring.boot.autoconfigure.KaptchaProperties;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Evil
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private KaptchaProperties kaptchaProps;
    private Producer producer;

    public CaptchaController(KaptchaProperties kaptchaProps, Producer producer) {
        this.kaptchaProps = kaptchaProps;
        this.producer = producer;
    }

    @RequestMapping("/image")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;

        String sessionCaptchaKey = kaptchaProps.getSession().getKey();

        String sessionCaptchaDate = kaptchaProps.getSession().getDate();
        HttpSession session = request.getSession();
        session.setAttribute(sessionCaptchaKey, capText);
        session.setAttribute(sessionCaptchaDate, System.currentTimeMillis());
        try {
            // write the data out
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            session.removeAttribute(sessionCaptchaKey);
            session.removeAttribute(sessionCaptchaDate);
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
    }

}

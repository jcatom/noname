package cc.jml1024.subdragon.controller;


import cc.jml1024.kaptcha.Producer;
import cc.jml1024.spring.boot.autoconfigure.KaptchaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * @author Evil
 */
@Controller
public class VerifyCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Producer producer;

    @Autowired
    private KaptchaProperties kaptchaProperties;

    @RequestMapping("/verifyCode/image")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);

        // fixes issue #69: set the attributes after we write the image in case the image writing fails.

        // store the text in the session
        request.getSession().setAttribute(kaptchaProperties.getSessionKey(), capText);

        // store the date in the session so that it can be compared
        // against to make sure someone hasn't taken too long to enter
        // their kaptcha
        request.getSession().setAttribute(kaptchaProperties.getSessionDate(), new Date());
    }

}

package cc.jml1024.noname.controller;


import cc.jml1024.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Evil
 */
@Controller
public class VerifyCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Producer producer;

    @RequestMapping("/verifyCode/image")
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
    }

}

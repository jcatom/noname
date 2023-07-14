package cc.jml1024.subdragon.security.filter;

import cc.jml1024.kaptcha.util.Config;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;


import java.io.IOException;

/**
 * @author Evil
 */
@Component
public class CaptchaVerifyFilter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String HOME_CAPTCHA_SESSION_KEY = "homeCaptcha";
    private final String FILTER_URI = "/login";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod()) &&
                FILTER_URI.equals(request.getServletPath())) {
            String verifyCode = request.getParameter("verifyCode");
            logger.info("session verifycode: [{}]", request.getSession().getAttribute(HOME_CAPTCHA_SESSION_KEY));
            if (verifyCode == null || "".equals(verifyCode)) {
                logger.error("验证码为空");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

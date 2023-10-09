package cc.jml1024.noname.security.filter;

import cc.jml1024.noname.commons.exception.CaptchaAuthenticationExcetion;
import cc.jml1024.spring.boot.autoconfigure.KaptchaProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Evil
 */
@Component
public class CaptchaAuthenticationFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String FILTER_URI = "/login/login";
    private KaptchaProperties kaptchaProps;

    private AuthenticationFailureHandler failureHandler;


    @Lazy
    public CaptchaAuthenticationFilter(KaptchaProperties kaptchaProps, AuthenticationFailureHandler failureHandler) {
        this.kaptchaProps = kaptchaProps;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod()) &&
                FILTER_URI.equals(request.getServletPath())) {
            String captcha = request.getParameter("captcha");
            if (captcha == null || "".equals(captcha)) {
                logger.error("验证码为空！");
                failureHandler.onAuthenticationFailure(request, response, new CaptchaAuthenticationExcetion("验证码不能为空！"));
                return;
            }
            String captchaSessionKey = kaptchaProps.getSession().getKey();
            String captchaSessionDate = kaptchaProps.getSession().getDate();
            HttpSession session = request.getSession();
            String sessionCaptcha = (String) session.getAttribute(captchaSessionKey);
            long sessionCaptchaDate = (long) session.getAttribute(captchaSessionDate);
            long timestamp = System.currentTimeMillis();
            if (captchaSessionKey == null || "".equals(sessionCaptcha) || timestamp - sessionCaptchaDate > 300000L) {
                logger.error("验证码已过期！");
                failureHandler.onAuthenticationFailure(request, response, new CaptchaAuthenticationExcetion("验证码已过期！"));
                return;
            }
            if (!sessionCaptcha.equalsIgnoreCase(captcha)) {
                logger.error("验证码错误！");
                failureHandler.onAuthenticationFailure(request, response, new CaptchaAuthenticationExcetion("验证码错误！"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}

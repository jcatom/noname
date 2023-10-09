package cc.jml1024.noname.security.filter;

import cc.jml1024.noname.commons.exception.CaptchaAuthenticationExcetion;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class UsernameAndPasswordVerifyFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String FILTER_URI = "/login/login";
    private final String USERNAME_PARAM_KEY = "username";
    private final String PASSWORD_PARAM_KEY = "password";

    private AuthenticationFailureHandler failureHandler;

    @Lazy
    public UsernameAndPasswordVerifyFilter(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod()) &&
                FILTER_URI.equals(request.getServletPath())) {
            String username = request.getParameter(USERNAME_PARAM_KEY);
            String password = request.getParameter(PASSWORD_PARAM_KEY);

            if (username == null || "".equals(username)) {
                logger.error("用户名为空！");
                failureHandler.onAuthenticationFailure(request, response, new CaptchaAuthenticationExcetion("用户名不能为空！"));
                return;
            }

            if (password == null || "".equals(password)) {
                logger.error("密码为空！");
                failureHandler.onAuthenticationFailure(request, response, new CaptchaAuthenticationExcetion("密码不能为空！"));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

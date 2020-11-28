package cc.jml1024.subdragon.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameAndPasswordVerifyFilter extends GenericFilterBean {

    private final String USERNAME_PARAM_KEY = "username";
    private final String PASSWORD_PARAM_KEY = "password";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = servletRequest.getParameter(USERNAME_PARAM_KEY);
        String password = servletRequest.getParameter(PASSWORD_PARAM_KEY);

        if (username == null || "".equals(username)) {

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}

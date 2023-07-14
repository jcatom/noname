package cc.jml1024.subdragon.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.web.filter.GenericFilterBean;

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

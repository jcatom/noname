package cc.jml1024.noname.commons.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author Evil
 */
public class CaptchaAuthenticationExcetion extends AuthenticationException {
    public CaptchaAuthenticationExcetion(String msg) {
        super(msg);
    }

    public CaptchaAuthenticationExcetion(String msg, Throwable cause) {
        super(msg, cause);
    }
}

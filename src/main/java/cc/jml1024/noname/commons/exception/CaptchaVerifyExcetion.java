package cc.jml1024.noname.commons.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author Evil
 */
public class CaptchaVerifyExcetion extends AuthenticationException {
    public CaptchaVerifyExcetion(String msg) {
        super(msg);
    }

    public CaptchaVerifyExcetion(String msg, Throwable cause) {
        super(msg, cause);
    }
}

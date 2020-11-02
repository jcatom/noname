package cc.jml1024.subdragon.commons.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author Evil
 */
public class CaptchaVerifyExcetion extends AuthenticationException {

    public CaptchaVerifyExcetion(String excetionMessage) {
        super(excetionMessage);
    }
}

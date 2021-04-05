package example.springcloudgateway.security;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTTokenExtractException extends JWTVerificationException {

    public JWTTokenExtractException(String message) {
        super(message);
    }
}
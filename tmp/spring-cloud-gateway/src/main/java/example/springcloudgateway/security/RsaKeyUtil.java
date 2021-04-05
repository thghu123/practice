package example.springcloudgateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Configuration
public class RsaKeyUtil {

    public static String RSA_ALGORITHM = "RSA";

    public static RSAPublicKey getPublicKey() {
        try {
            BigInteger b1 = new BigInteger("123");
            BigInteger b2 = new BigInteger("abc");
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPrivateKey getPrivateKey() {
        try {
            BigInteger b1 = new BigInteger("123");
            BigInteger b2 = new BigInteger("abc");
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}

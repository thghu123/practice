package example.jwtuserweb;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.CloseableThreadContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.Claim;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JWTTokenTest {

    @DisplayName("JWT token이 잘 생성된다")
    @Test
    void test(){

        //토큰 create.sign 을 하면 토큰이 발행
        //JWT.require은 검증 옵션

        String token = JWT.create()
                //사인하기 전에 옵션을 설정 가능
                .withSubject("gm")
                .withClaim("exp",Instant.now().getEpochSecond()+3)
                .sign(Algorithm.none());

        //result : eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.e30.
        //alg 알고리즘과 typ 타입 정보 헤더에 존재
        //바디에는 페이로드 존재, sub name iat 존재
        System.out.println(token);

        //decode는 어떻게 하는가?
        DecodedJWT decodedJWT = JWT.decode(token);

        printClaim("typ",decodedJWT.getHeaderClaim("typ"));
        printClaim("alg",decodedJWT.getHeaderClaim("alg"));

        decodedJWT.getClaims().forEach(JWTTokenTest::printClaim);

        //sub 값이 나오고 exp는 그대로 나온다.

   }

    static void printClaim(String key, Claim value){
        if(value.isNull()){
            System.out.printf("%s:%s\n", key, "none");
            return;
        }
        if(value.asString() != null){
            System.out.printf("%s:{str}%s\n", key, value.asString());
            return;
        }
        if(value.asLong() != null){
            System.out.printf("%s:{lng}%d\n", key, value.asLong());
            return;
        }
        if(value.asInt() != null ){
            System.out.printf("%s:{int}%d\n", key, value.asInt());
            return;
        }
        if(value.asBoolean() != null){
            System.out.printf("%s:{bol}%b\n", key, value.asBoolean());
            return;
        }
        if(value.asDate() != null){
            System.out.printf("%s:{dte}%s\n", key, value.asDate().toString());
            return;
        }
        if(value.asDouble() != null){
            System.out.printf("%s:{dbl}%f\n", key, value.asDouble());
            return;
        }
        String[] values = value.asArray(String.class);
        if(values != null){
            System.out.printf("%s:{arr}%s\n", key, Stream.of(values).collect(Collectors.joining(",")));
            return;
        }
        Map valueMap = value.asMap();
        if(valueMap != null) {
            System.out.printf("%s:{map}%s\n", key, valueMap);
            return;
        }
        System.out.println("====>> unknown type for :"+key);
    }

}

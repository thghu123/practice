package sample.springgateway.filter;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    //설정 파일(ex.application.yml)의 정보를 가져옴
    private final Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class); //부모의 class 정보
        this.env = env;
    }

    public static class Config {

    }

    //login -> token -> users (with token) -> header (include token)
    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            //요청 정보를 인증하는 작업을 하고, return chain으로 반환해준다.

            //헤더 인증
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");//bearer를 제외한 나머지 부분이 토큰 내용

            //토큰 인증
            if (!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
            //logic

        }));

    }

    //web X ->  Webflux , 데이터 타입 반환은 Mono, Flux로
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        //response 객체를 받아올 때 webflux -> ServerHttpResponse, Web -> ~selvet~로 받아온다.
        response.setStatusCode(httpStatus);
        //status set

        log.error(err);
        return response.setComplete();
    }

    //jwt valid?
    private boolean isJwtValid(String jwt) {

        Boolean returnValue = true;

        //jwt payload의 subject값으로 정상적인 값인지 판별
        String subject = null;


        try {
            //user에서 어떻게 subject 생성했는 지 확인해서 복호화
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    // parser 복호화 방법
                    .parseClaimsJws(jwt).getBody()
                    .getSubject(); //parser 복호화의 대상
        } catch (Exception exception) {
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        //추가 검증 사항 : 처음 입력 받은 ID를 비교해보는 logic

        return returnValue;
    }

}

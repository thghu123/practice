package sample.springgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//구동 확인 설정 해제 및 다시 yml route 설정 적용
//annotation만 빼줘도 구현하지 않은 것과 같음.

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        //yml 설정을 자바 설정으로 생성.
        return builder.routes()
                // r -> 함수 : 이라는 매개변수가 있을 때 어떤 동작을 할 지 정의
                //build 에서 chaining : 연속으로 설정할 수 있음을 의미
                .route(r -> r.path("/rtm/**") //" " 안의 path가 들어오면
                        .filters(f -> f.addRequestHeader("api-request","api-request-header")
                        .addResponseHeader("api-response","api-response-header"))
                        .uri("http://localhost:8081")) //"다음 path로 route 함.
                .route(r -> r.path("/blog/**") //" " 안의 path가 들어오면
                        .filters(f -> f.addRequestHeader("naver-request","naver-request-header")
                                .addResponseHeader("naver-response","naver-response-header"))
                        .uri("http://naver.com")) //"다음 path로 route 함.
                .build()
                ;

    }

}

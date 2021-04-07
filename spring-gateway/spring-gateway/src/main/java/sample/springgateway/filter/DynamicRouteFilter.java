//package sample.springgateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//
//@Component
//public class DynamicRouteFilter extends AbstractGatewayFilterFactory<DynamicRouteFilter.Config> {
//
//    public DynamicRouteFilter (){
//        super(Config.class); //부모의 class 정보
//    }
//
//    public static class Config {
//
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (((exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            //요청 정보를 인증하는 작업을 하고, return chain으로 반환해준다.
//            //confirm redis data instanceId data -> changed?
//            //요청 URL을 변경하는 부분, instance Id에 맞게 다른 Predicate 요청으로 들어가도록 설정한다.
//            try {
//                exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, new URI("lb://API-SERVICE"));
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_PREDICATE_ROUTE_ATTR/*GATEWAY_REQUEST_URL_ATTR*/, "/rtm/**");
//
//            return chain.filter(exchange);
//
//        }));
//
//    }
//
//}
//
//
////@Component
////public class DynamicRouteFilter implements GatewayFilter, Ordered {
////
////    @Override
////    public int getOrder(){
////        return 10001;
////    }
////
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
////
////    //confirm redis data instanceId data -> changed?
////
////        try{
////            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, new URI("http://naver.com"));
////
////        }catch(URISyntaxException e) {
////
////            e.printStackTrace();
////
////        }
////        return chain.filter(exchange);
////    }
////}

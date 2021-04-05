//package example.springjwtgateway.jwt_security_tmp;
//
//import example.springjwtgateway.accounts.AccountService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//@RequiredArgsConstructor
//public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
//    //인증 서버 설정
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final AuthenticationManager authenticationManager;
//    //유저 인증 정보를 가진 Auth 매니져, endpoint Configuration에 설정
//
//    private final AccountService accountService;
//    //userDetailService
//
//    private final TokenStore tokenStore;
//    //token 저장소를 의미
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        //사용할 Password Encoder를 설정
//        security.passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //CLIENT ID , grant_type , 유효 시간 설정
//        clients.inMemory()//in memory 내부 저장소를 통한 테스트
//                .withClient("myApp")
//                .secret(this.passwordEncoder.encode("pass")) //app의 secret을 의미, Test시 pass로 지정
//
//
//                .authorizedGrantTypes("password","refresh_token")
//                .refreshTokenValiditySeconds(6 * 10 * 60) //refresh 토근은 60분
//
//                /*
//                password grant type :
//                auth 토근 발급시 refresh token을 발급, 이를 통해 새로운 access 토근 얻는다.
////                Resource Owner가 직접 Client에 id, password 입력하고 서버에서 인증 받아 직접 토큰 얻어오는 방식
//
//                */
//
//                //.authorizedGrantTypes("authorization_code")
//                // service provider가 제공하는 인증 화면에서 로그인, rediect uri로 토큰과 code를 넘겨줌
//
//                //.redirectUris("http://localhost:5000/oauth/callback")
//                .scopes("read", "write")
//
//                .accessTokenValiditySeconds(10 * 60);//access 토근의 유효 시간, 10분
//
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(accountService)
//                .tokenStore(tokenStore);
//    }
//}

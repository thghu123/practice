package example.springjwtgateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
/*
    리소스 서버:
    web 요청이 들어왔을 때 resource라는 인증정보에 접근 권한이 있는 지 판별,
    인증서버와 리소스 서버를 분리하는 것이 좋다.
*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //리소스의 id를 설정
        resources.resourceId("api");

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //접근권한 없을 시의 처리, http 등을 설정
        http
                .anonymous() //익명 사용자 허용
                    .and()
                .authorizeRequests()
                    .mvcMatchers(HttpMethod.GET, "/actuator/**")
                        .permitAll()
                //get으로 들어온 요청 중 /api/를 통하는 요청은 허용
                //anonymous는 익명의 사용자만 접근을 허용
                    .anyRequest()
                        .authenticated()
                //그 외의 요청은 인증이 필요
                    .and()
                .formLogin()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(new OAuth2AccessDeniedHandler());

/*
                exception denied handler에 oauth access denied handler 추가
                인증이 잘못 되거나 권한이 없는 경우는 403 status로 이를 반환
*/

    }
}

package sample.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sample.userservice.service.UserService;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private Environment env;
//    private final AuthenticationFilter authenticationFilter;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll(); //인증없이 사용 가능
        http.authorizeRequests()
                .antMatchers("/**")
                //.authenticated() //인증 필요
//                .and()
                .hasIpAddress("127.0.0.1") //특정 ip 허용
                .and()
//                .permitAll().and() //전체 허용
                .addFilter(getAuthenticationFilter()); //인증 필터 추가

        http.headers().frameOptions().disable();//h2 사용시 frame 안뜨는 문제 해결
    }

    //설정한 인증 필터 적용
    private sample.userservice.security.AuthenticationFilter getAuthenticationFilter() throws Exception {

        AuthenticationFilter authenticationFilter =
                new sample.userservice.security.AuthenticationFilter(authenticationManager(),userService,env);
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}

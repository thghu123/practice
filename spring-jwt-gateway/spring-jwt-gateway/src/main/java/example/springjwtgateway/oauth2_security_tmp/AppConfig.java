package example.springjwtgateway.oauth2_security_tmp;

import example.springjwtgateway.accounts.AccountService;
import example.springjwtgateway.accounts.domain.Account;
import example.springjwtgateway.accounts.domain.AccountRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } //prefix를 패스워드 앞에 붙여 적절한 엔코더를 확인할 수 있다.


    //application 실행 시 계정 생성
    @Bean
    public ApplicationRunner applicationRunner(){

        return new ApplicationRunner() {

            @Autowired
            AccountService accountService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Account gyumin = Account.builder()
                        .email("gmhwang@ex-em.com")
                        .password("gm")
                        .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                        .build();
                accountService.saveAccount(gyumin);

            }
        };
    }
}

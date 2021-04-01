package example.springjwtgateway.accounts.domain;

import example.springjwtgateway.accounts.AccountService;
import example.springjwtgateway.accounts.repo.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @DisplayName("인증을 위한 UserDetails Service 가 잘 동작한다")
    @Test
    public void findByName(){
        //given
        String password = "gm";
        String username = "gmhwang@ex-em.com";
        Account account = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(account);

        //when
        UserDetailsService userDetailsService = accountService;
        //인증 서버에 입력한 테이블이 있는 지 확인
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //then
        Assertions.assertThat(this.passwordEncoder.matches(password,userDetails.getPassword()));
    }


    @DisplayName("유저 네임을 불러오지 못하는 경우")
    @Test
    public void findByUsernameFail(){
        String username = "jw@email.com";

        try {
            accountService.loadUserByUsername(username);
            fail("supposed to be failed");
        }catch(UsernameNotFoundException e){
            Assertions.assertThat(e.getMessage()).containsSequence(username);
        }


    }

}
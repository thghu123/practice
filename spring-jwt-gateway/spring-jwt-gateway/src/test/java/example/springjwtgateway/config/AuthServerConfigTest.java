package example.springjwtgateway.config;

import example.springjwtgateway.BaseControllerTest;
import example.springjwtgateway.accounts.AccountService;
import example.springjwtgateway.accounts.domain.Account;
import example.springjwtgateway.accounts.domain.AccountRole;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        /*
        인증 서버 :
        기존 토큰 방식은 매번 실제 서버에게 redirection이 일어나고 반환할 때도 일어난다.
        oauth2는 인증 정보가 있다면 이를 생략
        줘야하는 정보: grant_type , username, password 는 request의 파라미터로 전송, client_id, secret은 헤더에 포함
        인증정보를 보유하고 있다면 매번 grant type으로 매번 redirection 합을 맞추지 않고 접속이 가능하다.
        */

        String clientId = "myApp";
        String clientSecret = "pass";

        String username = "gm@naver.com";
        String password = "gm";

        Account gyumin = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(gyumin);
        //계정 등록

        this.mockMvc.perform(post("/oauth/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(clientId, clientSecret)) //basic oauth라는 헤더를 생성
                .param("username", username)
                .param("password",password)
                .param("grant_type", "password"))
                //grant_type : token을 가져오는 방법
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("access_token").exists());
                //요청을 보내면 access 토큰이 부여

/*
        테스트 결과 : Status expected:<200> but was:<400> 400 에러 출력
        invalid score exception
        server config 의 scopes("read","write") 설정을 "read write" 를 분리해 설정
        scope 입력시에는 분리해서 입력, 출력시에는 함께 출력된다.
*/

/*
        인증 서버 구현, 토큰 발급 가능, 토큰을 가지고
        resource에 접근 시 어떻게 사용할지는 resource 서버에서 결정. -> 설정 필요
*/

    }

}
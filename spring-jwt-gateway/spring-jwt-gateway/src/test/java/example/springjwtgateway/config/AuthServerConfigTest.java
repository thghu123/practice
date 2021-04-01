package example.springjwtgateway.config;

import example.springjwtgateway.BaseControllerTest;
import example.springjwtgateway.accounts.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken(){
        /*
        기존 토큰 방식은 매번 실제 서버에게 redirection이 일어나고 반환할 때도 일어난다.
        oauth2는 인증 정보가 있다면 이를 생략
        줘야하는 정보: grant_type , username, password 는 request의 파라미터로 전송, client_id, secret은 헤더에 포함
        인증정보를 보유하고 있다면 매번 grant type으로 매번 redirection 합을 맞추지 않고 접속이 가능하다.
        */

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(clientId,clientSecret))) //basic oauth라는 헤더를 생성
                .




    }

}
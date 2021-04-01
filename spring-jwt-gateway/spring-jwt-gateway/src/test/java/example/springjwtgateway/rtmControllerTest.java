package example.springjwtgateway;

import example.springjwtgateway.accounts.AccountService;
import example.springjwtgateway.accounts.domain.Account;
import example.springjwtgateway.accounts.domain.AccountRole;
import example.springjwtgateway.accounts.repo.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class rtmControllerTest extends BaseControllerTest{

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Before
    public void setUp(){
            accountRepository.deleteAll();
    }

    @Test
    @DisplayName("rtm RestAPI 테스트")
    public void rtmRestAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/rtm/slowquery")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + getAccessToken()))
                .andDo(print());
    }

    //인증 토큰을 받아오는 메서드
    public String getAccessToken() throws Exception {

        String username = "gm@naver.com";
        String password = "gm";

        Account gyumin = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(gyumin);

        String clientId = "myApp";
        String clientSecret = "pass";

        ResultActions perform = this.mockMvc.perform(post("/oauth/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(clientId, clientSecret)) //basic oauth라는 헤더를 생성
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password"));

        var responseBody = perform.andReturn().getResponse().getContentAsString();
        Jackson2JsonParser parser = new Jackson2JsonParser();
        return parser.parseMap(responseBody).get("access_token").toString();
        //access token return

    }
}
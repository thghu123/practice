package example.springjwtgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RtmController {
/*
    접근을 위해서 full authentiation 필요하고, token 에 포함돼있어야 접근이 가능하다.
    테스트 코드로 token header를 채워서 권한을 부여하는 것은 가능,
*/

    @GetMapping("/rtm/slowquery")
    public String slowquery(){
        return "{\"query_id\":\"query1\"}";
    }
}

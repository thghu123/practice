package example.springjwtgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rtmController {
    @GetMapping("/rtm/slowquery")
    public String slowquery(){
        return "{\"query_id\":\"query1\"}";
    }
}

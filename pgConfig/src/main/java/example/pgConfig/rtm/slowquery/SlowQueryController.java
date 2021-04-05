package example.pgConfig.rtm.slowquery;

import example.pgConfig.rtm.slowquery.rts.SlowQueryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rtm")
public class SlowQueryController {
//    private final SlowQueryCollector slowQueryCollector;

    @GetMapping("/slowquery/{instanceId}")
    public ResponseEntity<List<SlowQueryMessage>> slowQuery(@PathVariable long instanceId) {
//        return ResponseEntity.ok(slowQueryCollector.current(instanceId));
        return ResponseEntity.ok(List.of(
                SlowQueryMessage.builder()
                        .pid(954498)
                        .queryId("84d3faef-d68d-8bed-queryid1")
                        .elapsed(2403)
                        .build(),
                SlowQueryMessage.builder()
                        .pid(954498)
                        .queryId("84d3faef-d68d-8bed-queryid2")
                        .elapsed(1577)
                        .build(),
                SlowQueryMessage.builder()
                        .pid(954498)
                        .queryId("84d3faef-d68d-8bed-queryid3")
                        .elapsed(1801)
                        .build()
        ));
    }


    //api gateway와의 요청 응답에서 헤더값을 잘 주고 받는 지 확인
    @GetMapping("/message")
    public String message(@RequestHeader("api-request") String header) {
        log.info(header);
        return  "hello world in api service";
        //response header -> 웹 f12 개발자 도구 -> network -> 새로 고침 messgae에서 responose 확인
    }

}

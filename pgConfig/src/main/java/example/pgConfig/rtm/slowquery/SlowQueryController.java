package example.pgConfig.rtm.slowquery;

import example.pgConfig.rtm.slowquery.rts.SlowQueryMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SlowQueryController {
//    private final SlowQueryCollector slowQueryCollector;

    @GetMapping("/rtm/slowquery/{instanceId}")
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
}

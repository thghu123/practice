package sample.userservice.controller;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.userservice.dto.UserDto;
import sample.userservice.service.UserService;
import sample.userservice.vo.RequestUser;
import sample.userservice.vo.ResponseUser;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/active-check")
    public String status() {
        return String.format("user management service's working on PORT: %s",env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    //user 정보와 함께 요청 시 user 정보를 저장, 요청은 @Requestbody 형태로 날아오기에 해당 타입으로 받는다.
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {

        //Dto를 넘겨주기 위해 다시 user를 DTO로 변경
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);

        //jpa 저장
        userService.createUser(userDto);

        //회원 생성 정보 출력 -> ResponseUser
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class); //user dto에서 responseUser로 변환

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
        //200이 아닌 201이라는 성공 코드 + 정보

        //test : postman에서 POST 방식으로 테스팅 - /users + 회원정보 전달
    }



}

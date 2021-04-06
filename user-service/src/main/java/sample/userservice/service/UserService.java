package sample.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sample.userservice.dto.UserDto;

public interface UserService extends UserDetailsService {
    //사용하고자 하는 내용 : Dto 형태를 Entity로 변환 -> jpa 저장으로 회원가입
    UserDto createUser(UserDto userDto);

    UserDto getUserDetailsByEmail(String userName);
}

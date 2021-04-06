package sample.userservice.service;

import sample.userservice.dto.UserDto;

public interface UserService {
    //사용하고자 하는 내용 : 변환
    UserDto createUser(UserDto userDto);
}

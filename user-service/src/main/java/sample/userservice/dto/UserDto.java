package sample.userservice.dto;

import lombok.Data;

import java.util.Date;


//들어온 요청을 변환 DB 혹은 다른 플랫폼으로 이동 시키는 용도의 DTO
@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encrytedPwd;
}

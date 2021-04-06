package sample.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {

    @NotNull(message = "email cannot ba null")
    @Size(min = 2, message = "email not be less then 2 characters")
    @Email
    private String email;

    @NotNull(message = "password cannot ba null")
    @Size(min = 8, message = "password not be less then 8 characters")
    private String password;
}

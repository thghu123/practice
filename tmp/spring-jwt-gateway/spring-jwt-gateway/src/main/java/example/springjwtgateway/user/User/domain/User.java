//package example.springjwtgateway.jpa.User.domain;
//
//import example.springjwtgateway.jpa.UserRole.domain.UserRole;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Entity(name = "mxg_user")
//public class User{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long id;
//
//    private String name;
//
//    private String email;
//
//    private String password;
//
//    private String phone,
//            company;
//
//    @Enumerated(EnumType.STRING)
//    private Language language;
//    //STRING: 문자열 자체가 저장됨 ORDINAL: 상승하는 숫자가 저장됨
//
//    @Enumerated(EnumType.STRING)
//    private Timezone timeZone;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<UserRole> userRoles = new ArrayList<>();
//
//
//    @Builder
//    public User(String name, String email, String password, String phone, String company, Language language, Timezone timezone){
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phone = phone;
//        this.company = company;
//        this.language = language;
//        this.timeZone = timezone;
//    }
//
//    public void encodePassword(PasswordEncoder passwordEncoder) {
//        password = passwordEncoder.encode(password);
//    }
//
//}

package example.pgConfig;

import example.pgConfig.jpa.User.domain.Language;
import example.pgConfig.jpa.User.domain.Timezone;
import example.pgConfig.jpa.User.domain.User;
import example.pgConfig.jpa.User.repository.UserRepository;
import example.pgConfig.jpa.UserRole.domain.UserRole;
import example.pgConfig.jpa.UserRole.repository.UserRoleRepository;
import example.pgConfig.rtm.slowquery.rts.SlowQueryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    @Async
    public void run(ApplicationArguments args) throws InterruptedException {
        log.info("INSERT STARTED");

        User user = User.builder()
                .company("exem")
                .email("gmhwang@ex-em.com")
                .language(Language.KO)
                .phone("01020421234")
                .timezone(Timezone.UTC)
                .password("1234")
                .name("gm")
                .build();

        UserRole userRole = UserRole.builder()
                .user(user)
                .role("USER")
                .build();

        user.encodePassword(passwordEncoder);

        userRepository.save(user);
        userRoleRepository.save(userRole);

    }





}

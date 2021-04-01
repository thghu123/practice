package example.pgConfig.jpa;

import example.pgConfig.jpa.User.domain.Language;
import example.pgConfig.jpa.User.domain.Timezone;
import example.pgConfig.jpa.User.domain.User;
import example.pgConfig.jpa.User.repository.UserRepository;
import example.pgConfig.jpa.UserRole.domain.UserRole;
import example.pgConfig.jpa.UserRole.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class jpaPGTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Transactional
    @DisplayName("jpa로 postgresql 입력이 잘되는 지 확인")
    @Test
    public void test_transaction() throws InterruptedException {

        String name = "gmhwang";
        String email = "gmhwang@ex-em.com";
        String password = "4321";

        User user = User.builder()
                .company("exem")
                .email(email)
                .language(Language.KO)
                .phone("01020421234")
                .timezone(Timezone.UTC)
                .password(password)
                .name(name)
                .build();

        userRepository.save(user);

        userRoleRepository.save(UserRole.builder()
                .user(user)
                .role("USER")
                .build());

        List<User> persistedUser = userRepository.findAll();
        List<UserRole> persistedUserRole = userRoleRepository.findAll();

        assertThat(persistedUser.get(0).getName()).isEqualTo(name);
        assertThat(persistedUser.get(0).getPassword()).isEqualTo(password);
        assertThat(persistedUser.get(0).getEmail()).isEqualTo(email);
    }

}
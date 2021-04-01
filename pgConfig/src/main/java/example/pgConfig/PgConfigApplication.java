package example.pgConfig;

import example.pgConfig.jpa.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@Transactional
public class PgConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgConfigApplication.class, args);
	}

}

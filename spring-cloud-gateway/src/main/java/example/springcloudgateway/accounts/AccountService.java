package example.springcloudgateway.accounts;

import example.springjwtgateway.accounts.domain.Account;
import example.springjwtgateway.accounts.domain.AccountRole;
import example.springjwtgateway.accounts.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account saveAccount(Account account) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        return this.accountRepository.save(account);
    } //저장 시 엔코더를 적용

    @Override
    public UserDetails loadUserByUsername(String username) {

        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        //account를 userDetails와 매치, 상속을 받아 Custom user 를 사용할 수 있음.
        return new User(account.getEmail(), account.getPassword(), authorities(account.getRoles()));

    }

    //role to grantauth for spring security format
    private Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
        return roles.stream().map(r ->
            new SimpleGrantedAuthority("ROLE_" + r.name())
            ).collect(Collectors.toSet());
    }

}

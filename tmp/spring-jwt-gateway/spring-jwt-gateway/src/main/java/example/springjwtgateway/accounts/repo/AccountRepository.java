package example.springjwtgateway.accounts.repo;

import example.springjwtgateway.accounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {


    Optional<Account> findByEmail(String username);
    //Optinal : exception for null

}

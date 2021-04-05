//package example.springjwtgateway.security;
//
//import example.springjwtgateway.security.domain.AuthenticatedUser;
//import example.springjwtgateway.jpa.User.domain.User;
//import example.springjwtgateway.jpa.User.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import static java.lang.String.format;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
////    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userRepository
//                .findByName(username)
//                .orElseThrow(() -> new UsernameNotFoundException(getUserNotFoundMessage(username)));
//
//        return AuthenticatedUser.from(user);
//    }
//
//    private String getUserNotFoundMessage(String username) {
//        return format("User with username '%s', not found", username);
//    }
//}

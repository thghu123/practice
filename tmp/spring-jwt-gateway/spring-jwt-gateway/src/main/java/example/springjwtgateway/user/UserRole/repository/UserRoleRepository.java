//package example.springjwtgateway.jpa.UserRole.repository;
//
//import example.springjwtgateway.jpa.UserRole.domain.UserRole;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//
//    @Query("select ur from UserRole ur where ur.user.id = :userId")
//    List<UserRole> findAllByUserId(long userId);
//}

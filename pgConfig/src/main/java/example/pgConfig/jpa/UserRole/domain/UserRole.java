package example.pgConfig.jpa.UserRole.domain;

import example.pgConfig.jpa.User.common.AuditEntity;
import example.pgConfig.jpa.User.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRole extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role_name")
    private String role;

    @Builder
    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

}

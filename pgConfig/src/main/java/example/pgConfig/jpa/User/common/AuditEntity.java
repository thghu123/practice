package example.pgConfig.jpa.User.common;

/*
    Spring data 는 누군가 entity를 생성 또는 수정할 때 변화를 감지하고 이를
    사용자가 감지할 수 있게하는 기능을 제공한다.

    어떤 사용자가 entity 생성, 수정 : Create by , Last Modified by
    언제 entity 생성, 수정 : create date, last modified data 가 있다.

*/

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 추상 클래스 정의 사용할 필드를 각각 지정하고, 이를 상속하는 엔티티를 생성해준다.

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @CreatedDate
    private LocalDateTime createDate;


    @LastModifiedBy
    private String createdBy;

    @CreatedBy
    private String modifiedBy;

}

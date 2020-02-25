package hachi.flspringboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 모든 Entity의 상위클래스
 * Entity 의 createdDate, modifiedDate 를 자동으로 관리하는 역할
 */
@Getter
@MappedSuperclass // jpa Entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들도 칼럼으로 인식할 수 있게 선언
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

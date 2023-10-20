package accountbook;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter @Setter
@MappedSuperclass // 해당 클래스를 슈퍼클래스로 선언
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변화를 감지해주는
public class BaseTime {
    @CreatedDate
    private LocalDateTime cdate;    // 레코드, 엔티티의 생성날짜

    @LastModifiedDate
    private LocalDateTime udate;    // 레코드, 엔티티의 수정날짜
}

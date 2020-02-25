package hachi.flspringboot.domain.posts;

import hachi.flspringboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity 클래스
 */
@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 알려줌
public class Posts extends BaseTimeEntity {

    @Id // 해당 PK 필드를 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, spring boot 2.0 이후부터 GenerationType.IDENTITY 를 붙여야 auto_increment 가 됨
    private Long id;

    @Column(length = 500, nullable = false) //굳이 선언하지 않아도 기본으로 Column 으로 되지만 추가 옵션이 필요할 시에 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //생성자로 주입받는 방식
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

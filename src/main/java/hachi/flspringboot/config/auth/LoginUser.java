package hachi.flspringboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target; //어노테이션이 생성될 수 있는 위치 지정

@Target(ElementType.PARAMETER) //메소드의 파라미터로 선언된 객체만 사용할 수 있음
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}

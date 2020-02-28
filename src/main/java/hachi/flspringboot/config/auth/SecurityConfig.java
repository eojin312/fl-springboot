package hachi.flspringboot.config.auth;

import hachi.flspringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션들
                    //권한 관리 대상을 지정하는 옵션들
                    .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**").permitAll()
                    //POST 메소드이면서 "/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 가능하도록
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()//설정된 값들 이외 나머지 URL 들을 나타냄
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);


    }



}

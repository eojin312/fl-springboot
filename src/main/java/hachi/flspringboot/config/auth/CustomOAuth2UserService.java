package hachi.flspringboot.config.auth;

import hachi.flspringboot.config.auth.dto.SessionUser;
import hachi.flspringboot.domain.user.User;
import hachi.flspringboot.domain.user.UserRepository;
import hachi.flspringboot.web.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
    public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        // registrationID = 현재 로그인 진행ㅈ 중인 서비스를 구분하는 코드
        // 나중에 네이버, 카카오 연동시에 구글로그인인지 네이버 로그인인지 구분하기위해 사용
        // userNameAttributeName = OAuth2 로그인 진행 시 키가 되는 필드값 (= PrimaryKey 와 같은 의미)
        String registrationID = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                    .getUserInfoEndpoint()
                        .getUserNameAttributeName();


        // OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes
                .of(
                        registrationID
                        ,userNameAttributeName
                        ,oAuth2User.getAttributes()
                );

        User user = saveOrUpdate(attributes);

        /**
         * SessionUser = 세션에 사용자 정보 저장 용도로 사용
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    /**
     * 최초 oauth로그인 시에는 save 즉 insert해서 가입이 처리되고,
     * 두번째 oauth로그인 부터는
     *   1. 이메일로 가입된 여부를 확인하고
     *   2. 있으면 이름과 프사를 업데이트 해준다
     *   그리고 update한다ㅏ.
     * @param attributes
     * @return
     */
    private User saveOrUpdate(OAuthAttributes attributes) {
        // 이메일로
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity
                        ->
                        entity.update(attributes.getName(), attributes.getPicture())
                )
                .orElse(attributes.toEntity());

        // 위 코드를 풀어쓰면 아래와 같음
/*        Optional<User> user = userRepository.findByEmail(attributes.getEmail());

        if (user.isEmpty() == true) {
            user = Optional.of(attributes.toEntity());
        }

        String newName = attributes.getName();
        String newPicture = attributes.getPicture();
        user.get().update(newName, newPicture);*/

        return userRepository.save(user);
    }
}

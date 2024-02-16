package com.assu.study.chap0601.service;

import com.assu.study.chap0601.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

  private final JpaUserDetailsService jpaUserDetailsService;
  // delegatingPasswordEncoding 적용 시
//  private final BCryptPasswordEncoder bCryptPasswordEncoder;
//  private final SCryptPasswordEncoder sCryptPasswordEncoder;
  private final PasswordEncoder passwordEncoder;

  /**
   * 인증 논리 구현
   * <p>
   * 사용자 이름에 맞는 사용자를 로드한 후 암호가 DB 에 저장된 해시와 일치하는지 검증
   * 검증 작업은 사용자 암호를 해시하는데 이용된 알고리즘에 따름
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    // UserDetailsService 에서 사용자 검색
    CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(username);

    Authentication result;
    try {
      // delegatingPasswordEncoding 적용 시
      result = checkPassword(user, password, passwordEncoder, user.getUser().getAlgorithm().toString());

//      result = switch (user.getUser().getAlgorithm()) {
//        case BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder);
//        case SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder);
//      };
    } catch (Exception e) {
      e.printStackTrace();
      throw new BadCredentialsException("Bad credentials...! (authenticate())", e);
    }

    return result;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    // UsernamePasswordAuthenticationToken 는 Authentication 인터페이스의 한 구현이며,
    // 사용자 이름과 암호를 이용하는 표준 인증 요청을 나타냄
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  /**
   * 매개 변수로 전달된 PasswordEncoder 를 사용하여 사용자 입력으로 받은 원시 암호가 DB 의 인코딩과 일치하는지 검증 후
   * 암호가 올바르면 Authentication 계약의 구현을 인스턴스로 반환
   */
  private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder, String algorithm) {
    // delegatingPasswordEncoding 적용 시
    String userPassword = "{" + algorithm.toLowerCase() + "}" + user.getPassword();

    // 인코딩된 문자열(암호)이 원시 암호(rawPassword) 와 일치하는지 확인
    if (encoder.matches(rawPassword, userPassword)) {
      // 암호가 일치하면 AuthenticationProvider 는
      // 필요한 세부 정보가 담긴 Authentication 계약의 구현을 '인증됨' 으로 표시한 후 반환함
      return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    } else {
      // 암호가 일치하지 않으면 AuthenticationException 형식의 예외 발생
      throw new BadCredentialsException("Bad credentials..!");
    }
  }
}

package com.assu.study.chap1101.service;

import com.assu.study.chap1101.entity.Otp;
import com.assu.study.chap1101.entity.User;
import com.assu.study.chap1101.repository.OtpRepository;
import com.assu.study.chap1101.repository.UserRepository;
import com.assu.study.chap1101.util.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final OtpRepository otpRepository;

  // 사용자 추가
  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  // 사용자 인증 후 OTP 생성하여 SMS 를 통해 발송
  public void auth(User user) {
    Optional<User> oUser = userRepository.findByUsername(user.getUsername());

    // 사용자가 있으면 암호를 확인 후 새로운 OTP 생성
    // 사용자가 없거나 암호가 틀리면 예외 발생
    if (oUser.isPresent()) {
      User u = oUser.get();
      if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
        renewOtp(u);
      } else {
        throw new BadCredentialsException("Bad Credentials");
      }
    } else {
      throw new BadCredentialsException("Bad Credentials");
    }
  }

  // 새로운 OTP 생성
  private void renewOtp(User user) {
    String code = GenerateCodeUtil.generateCode();

    Optional<Otp> userOtp = otpRepository.findByUsername(user.getUsername());

    // 이 사용자에 대한 otp 가 있으면 otp 값 업데이트
    Otp otp;
    if (userOtp.isPresent()) {
      otp = userOtp.get();
    } else {
      otp = new Otp();
      // 이 사용자에 대한 opt 가 없으면 새로 생성된 값으로 레코드 생성
      otp.setUsername(user.getUsername());
    }
    otp.setCode(code);

    otpRepository.save(otp);
  }

  // 사용자의 OTP 검증
  public boolean check(Otp otp) {
    // 사용자 이름으로 OTP 검색
    Optional<Otp> userOtp = otpRepository.findByUsername(otp.getUsername());

    if (userOtp.isPresent()) {
      Otp o = userOtp.get();
      // DB 에 OTP 가 있고, 비즈니스 논리 서버에서 받은 OTP 와 일차하면 true 반환
      return otp.getCode().equals(o.getCode());
    }
    return false;
  }
}

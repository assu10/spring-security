package com.assu.study.chap1101.repository;

import com.assu.study.chap1101.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
  Optional<Otp> findByUsername(String username);
}

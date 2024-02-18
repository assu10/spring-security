package com.assu.study.chap1003.repository;

import com.assu.study.chap1003.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Integer> {
  Optional<Token> findByIdentifier(String identifier);
}

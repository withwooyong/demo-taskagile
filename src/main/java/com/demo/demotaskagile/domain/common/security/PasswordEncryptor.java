package com.demo.demotaskagile.domain.common.security;

public interface PasswordEncryptor {

  /**
   * Encrypt a raw password
   */
  String encrypt(String rawPassword);
}

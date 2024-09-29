package com.kugelblitz.bazaar.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidatorUtilTest {

  @Test
  public void testValidatePassword() {
    String validPassword = "Valid@123";
    assertFalse(ValidatorUtil.isNotValidPattern(validPassword, ValidatorUtil.PASSWORD_PATTERN));

    String invalidPassword = "Invalid";
    assertTrue(ValidatorUtil.isNotValidPattern(invalidPassword, ValidatorUtil.PASSWORD_PATTERN));
  }

  @Test
  public void testValidateEmail() {
    String validEmail = "valid@email.com";
    assertFalse(ValidatorUtil.isNotValidPattern(validEmail, ValidatorUtil.EMAIL_PATTERN));

    String invalidEmail = "invalidemail.com";
    assertTrue(ValidatorUtil.isNotValidPattern(invalidEmail, ValidatorUtil.EMAIL_PATTERN));
  }
}

package com.kugelblitz.bazaar.util;

import java.util.regex.Pattern;

public class ValidatorUtil {

  // Predefined patterns can still be constants for reuse
  public static final String PASSWORD_PATTERN =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
  public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{3,15}$";

  // Generalized method for validating any pattern
  public static boolean isNotValidPattern(String input, String pattern) {
    return !Pattern.compile(pattern).matcher(input).matches();
  }
}

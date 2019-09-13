package com.vokovalenko16.framework.tool;

import com.vokovalenko16.framework.component.CustomPasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Generate encode password.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 6/29/15
 * @since JDK1.8
 */
public class GenerateEncodedPassword {

  private static final String RAW_PASSWORD = "alice123";

  public static void main(String[] args) {
    String encodedPassword = new CustomPasswordEncoder().encode(RAW_PASSWORD);
    System.out.println(encodedPassword);
  }
}

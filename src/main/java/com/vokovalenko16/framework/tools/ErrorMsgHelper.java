package com.vokovalenko16.framework.tools;

import com.vokovalenko16.framework.enums.ErrorType;

/**
 * Format the error msg.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 11/6/15
 * @since JDK1.8
 */
public class ErrorMsgHelper {

  /**
   * Get return error message.
   *
   * @param msg  error type
   * @param args args
   * @return return error message
   */
  public static String getReturnMsg(ErrorType msg, String... args) {
    final String COLON = ": ";
    final String PREFIX = String.join("", msg.name(), COLON);
    if (args != null) {
      return String.join("", PREFIX, String.format(msg.description(), (Object) args));
    } else {
      return String.join("", PREFIX, msg.description());
    }
  }

}

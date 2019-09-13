package com.vokovalenko16.framework.exception;

import com.vokovalenko16.framework.enums.ErrorType;

/**
 * Commons exception.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 11/5/15
 * @since JDK1.8
 */
public class CommonsException extends SystemException {

  private static final long serialVersionUID = -1041148006846583767L;

  private final static ErrorType ERROR_TYPE = ErrorType.SYS0100;

  public CommonsException(ErrorType type, Throwable t, String errorMsg) {
    super(type, t, errorMsg);
  }

  public CommonsException(ErrorType type, String errorMsg) {
    super(type, errorMsg);
  }

  public CommonsException(ErrorType type) {
    super(type);
  }

  public CommonsException() {
    super(ERROR_TYPE);
  }
}

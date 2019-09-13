package com.vokovalenko16.framework.exception;


import com.vokovalenko16.framework.enums.ErrorType;

/**
 * Unknown exception.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 7/26/15
 * @since JDK1.8
 */
public class UnknownException extends SystemRuntimeException {

  private static final long serialVersionUID = -7431810328087316293L;

  private final static ErrorType ERROR_TYPE = ErrorType.UNKNOWN;

  public UnknownException() {
    super(ERROR_TYPE);
  }
}

package com.vokovalenko16.framework.enums;

/**
 * Intent state interface.
 * <pre>
 *     String code;
 *     String description;
 * </pre>
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/29/15
 * @since JDK1.8
 */
public interface IntentStateWithCodeAndDescription {

  int code();

  String description();
}

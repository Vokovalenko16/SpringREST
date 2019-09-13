package com.vokovalenko16.framework.vo;

import java.io.Serializable;

/**
 * VO for {@link com.vokovalenko16.framework.controller.WelcomeController}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 6/25/15
 * @since JDK1.8
 */
public class WelcomeVO implements Serializable {

  private static final long serialVersionUID = -7734398342573960351L;

  private final long id;

  private final String content;

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer("WelcomeVO{");
    sb.append("id=").append(id);
    sb.append(", content='").append(content).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public WelcomeVO(long id, String content) {
    this.id = id;
    this.content = content;
  }

}

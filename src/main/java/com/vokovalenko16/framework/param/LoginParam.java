package com.vokovalenko16.framework.param;

import com.vokovalenko16.framework.annotation.NotNullField;
import com.vokovalenko16.framework.enums.GrantType;
import javax.validation.constraints.Size;

/**
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 15/02/2017
 * @since JDK1.8
 */
public class LoginParam extends BaseParam {

  private static final long serialVersionUID = 1148462952236125805L;

  @NotNullField(grant = GrantType.PASSWORD, message = "usr cannot be null.")
  @Size(min = 4, max = 50, message = "usr must greater than or equal to 4 and less than or equal to 50.")
  private String usr;

  @NotNullField(grant = GrantType.PASSWORD, message = "pwd cannot be null.")
  @Size(min = 4, max = 16, message = "pwd must greater than or equal to 4 and less than or equal to 16.")
  private String pwd;

  @NotNullField(grant = GrantType.REFRESH_TOKEN, message = "refresh token cannot be null.")
  private String refreshToken;

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer(super.toString());
    sb.append("LoginParam{");
    sb.append("usr='").append(usr).append('\'');
    sb.append(", pwd='").append(pwd).append('\'');
    sb.append(", refreshToken='").append(refreshToken).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public String getUsr() {
    return usr;
  }

  public void setUsr(String usr) {
    this.usr = usr;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}

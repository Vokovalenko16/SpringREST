package com.vokovalenko16.framework.param;

import com.vokovalenko16.framework.enums.OperationType;

import java.io.Serializable;

/**
 * Param bean for {@link com.vokovalenko16.framework.domain.LogDomain}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/28/15
 * @since JDK1.8
 */
public class LogParam implements Serializable {

  private static final long serialVersionUID = 891050528216283300L;

  private String ip;

  private OperationType type;

  private String clientId;

  private String accessResource;

  public LogParam() {}

  public LogParam(String ip, OperationType type, String clientId, String accessResource) {
    this.ip = ip;
    this.type = type;
    this.clientId = clientId;
    this.accessResource = accessResource;
  }

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer(super.toString());
    sb.append("LogParam{");
    sb.append("ip='").append(ip).append('\'');
    sb.append(", type=").append(type);
    sb.append(", clientId='").append(clientId).append('\'');
    sb.append(", accessResource='").append(accessResource).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public OperationType getType() {
    return type;
  }

  public void setType(OperationType type) {
    this.type = type;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getAccessResource() {
    return accessResource;
  }

  public void setAccessResource(String accessResource) {
    this.accessResource = accessResource;
  }
}

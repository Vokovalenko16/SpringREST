package com.vokovalenko16.framework.component;

import com.vokovalenko16.framework.domain.LogDomain;
import com.vokovalenko16.framework.enums.OperationType;
import com.vokovalenko16.framework.param.LogParam;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.tools.RemoteAddressUtils;
import com.vokovalenko16.framework.tools.SpringSecurityUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Log users' operations.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 11/6/15
 * @since JDK1.8
 */
@Component public class LogHelper {

  public LogHelper(HttpServletRequest request, LogDomain logDomain) {
    Assert.defaultNotNull(request);
    Assert.defaultNotNull(logDomain);
    this.request = request;
    this.logDomain = logDomain;
  }

  public void logUsersOperations(OperationType operationType, String resource, User currentUser) throws Exception {
    // Get ip and clientId
    String ip = RemoteAddressUtils.getRealIp(request);
    ip = StringUtils.isBlank(ip) ? "0.0.0.0.0.0.0.0:1" : ip;
    String clientId = SpringSecurityUtils.getCurrentUsername();

    // Log users' operations.
    logDomain.create(new LogParam(ip, operationType, clientId, resource), currentUser);
  }

  private final HttpServletRequest request;

  private final LogDomain logDomain;

}

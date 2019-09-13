package com.vokovalenko16.framework.tools;

import com.vokovalenko16.framework.enums.GrantType;
import com.vokovalenko16.framework.param.LoginParam;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * Login utils.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 08/04/2017
 * @since JDK1.8
 */
public class LoginUtils {

  public static String getClientId(HttpServletRequest request) {
    String decodedStr = new String(Base64.decodeBase64(request.getHeader(AUTHORIZATION).substring(5)));
    return decodedStr.substring(0, decodedStr.indexOf(COLON));
  }

  public static Map<String, String> getParams(LoginParam param) {
    Map<String, String> map = new HashMap<>();
    if (StringUtils.isNotBlank(param.getUsr())) {
      map.put(USERNAME, param.getUsr());
    }
    if (StringUtils.isNotBlank(param.getPwd())) {
      map.put(PASSWORD, param.getPwd());
      map.put(GRANT_TYPE, GrantType.PASSWORD.description());
    }
    if (StringUtils.isNotBlank(param.getRefreshToken())) {
      map.put(REFRESH_TOKEN, param.getRefreshToken());
      map.put(GRANT_TYPE, GrantType.REFRESH_TOKEN.description());
    }
    map.put(SCOPE, READ);
    return map;
  }

  private static final String AUTHORIZATION = "Authorization";
  private static final String COLON = ":";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";
  private final static String REFRESH_TOKEN = "refresh_token";
  private static final String GRANT_TYPE = "grant_type";
  private static final String SCOPE = "scope";
  private static final String READ = "read";

}

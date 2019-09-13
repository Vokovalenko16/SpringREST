package com.vokovalenko16.framework.service;

import com.vokovalenko16.framework.param.LoginParam;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * Service for register.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 23/01/2017
 * @since JDK1.8
 */
public interface LoginService {

  ResponseEntity login(LoginParam param, HttpServletRequest request) throws Exception;

  ResponseEntity refresh(LoginParam param, HttpServletRequest request) throws Exception;

}

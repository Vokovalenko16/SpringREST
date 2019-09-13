package com.vokovalenko16.framework.config;

import com.vokovalenko16.framework.config.custom.CustomAuthenticationProvider;
import com.vokovalenko16.framework.tools.Assert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring web security config.
 * Extends {@link WebSecurityConfigurerAdapter}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 6/25/15
 * @since JDK1.8
 */
@Configuration @EnableWebSecurity @EnableSpringDataWebSupport
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override @Bean public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean public AuthenticationProvider authenticationProvider() {
    return customAuthenticationProvider;
  }

  private final CustomAuthenticationProvider customAuthenticationProvider;

  public WebSecurityConfiguration(CustomAuthenticationProvider customAuthenticationProvider) {
    Assert.defaultNotNull(customAuthenticationProvider);
    this.customAuthenticationProvider = customAuthenticationProvider;
  }

}

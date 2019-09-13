package com.vokovalenko16.framework.config;

import com.vokovalenko16.framework.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring boot servlet initializer.
 * Extends {@link SpringBootServletInitializer}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 6/25/15
 * @since JDK1.8
 */
public class WebInitializer extends SpringBootServletInitializer {

  @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

}

package com.colorofhope.ayitili.rest.config;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class RequestConfig {
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver() {
      @Override
      public boolean isMultipart(HttpServletRequest request) {
        String method = request.getMethod().toLowerCase();
        // By default, only POST is allowed. Since this is an 'update' we should accept PUT.
        if (!Arrays.asList("put", "post").contains(method)) {
          return false;
        }
        String contentType = request.getContentType();
        return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
      }
    };
  }
}

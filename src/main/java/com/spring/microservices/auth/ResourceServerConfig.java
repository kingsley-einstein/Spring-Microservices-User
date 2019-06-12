package com.spring.microservices.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${auth.resource-id}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
        configurer.resourceId(resourceId)
        .stateless(false);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/**/**/auth/signup", "/**/**/auth/signin", "/oauth/token")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/actuator/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
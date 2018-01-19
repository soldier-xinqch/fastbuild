package com.fastbuild.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * oauth2 资源配置
 *
 * @auther xinch
 * @create 2018/1/16 13:15
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Configuration
public class AuthResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth2/api/**").authorizeRequests()
                .antMatchers(HttpMethod.GET, "/read/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/write/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/write/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/write/**").access("#oauth2.hasScope('write')");
    }
}

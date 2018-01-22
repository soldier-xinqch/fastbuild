package com.fastbuild.auth.config;

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
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthResourceConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "OAUTH2_RESOURCEID_XINQCH";

    private static final String AUTH_TOKEN_JWT_KEY = "OAUTH2_TOKEN_XINQCH";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        resources.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        // 公钥
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        Resource resource = new ClassPathResource("public.txt");
//        String publicKey = null;
//        try {
//            publicKey = inputStream2String(resource.getInputStream());
//        } catch (final IOException e) {
//            throw new RuntimeException(e);
//        }
//        converter.setVerifierKey(publicKey);
//        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
//        return converter;
//    }
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(AUTH_TOKEN_JWT_KEY);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth2/api/**").authorizeRequests()
                .antMatchers(HttpMethod.GET, "/read/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/write/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/write/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/write/**").access("#oauth2.hasScope('write')");
    }
}

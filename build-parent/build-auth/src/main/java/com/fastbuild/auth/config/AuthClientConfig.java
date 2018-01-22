package com.fastbuild.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fastbuild.auth.service.IAuthClientService;
import com.fastbuild.auth.service.impl.AuthClientServiceImpl;
import com.fastbuild.auth.service.impl.AuthCustomTokenServiceImpl;
import com.fastbuild.auth.utils.RedisAuthenticationCodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * oauth2 客户端配置
 *
 * @auther xinch
 * @create 2018/1/16 13:15
 */
@Configuration
@EnableAuthorizationServer
public class AuthClientConfig extends AuthorizationServerConfigurerAdapter {

    private static final String AUTH_TOKEN_JWT_KEY = "OAUTH2_TOKEN_XINQCH";

    private static final String DEMO_RESOURCE_ID = "OAUTH2_RESOURCEID_XINQCH";

    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Resource(name ="authClientService")
    private IAuthClientService authClientService;

    @Resource(name = "masterDataSource")
    public DruidDataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//                .and() // 兼容模式
//                .withClient("client_test")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("select","read","write")
//                .secret("123456")
//                .authorities("client")
//                .accessTokenValiditySeconds(360000)
//                .and()
//                .withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code","password","implicit","client_credentials")
//                .scopes("all")
//                .secret("123456")
//                .authorities("client")
//                .accessTokenValiditySeconds(360000)
//                .and()
//                .withClient("client")
//                .scopes("read","write")
//                .secret("secret")
//                .authorities("client")
//                .accessTokenValiditySeconds(360000)
//                .authorizedGrantTypes("authorization_code","password","implicit","client_credentials");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        super.configure(oauthServer);
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }

    /**
     *  设置端点信息
     *  https://lidong1665.github.io/2017/03/14/Spring-Security-OAuth2-%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97/
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers( Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                // token
                .tokenEnhancer(tokenEnhancerChain)
                // 授权码
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager);

        endpoints.setClientDetailsService(authClientService);
        /**
         *  替换默认链接
         */
        // 定制获取token
        endpoints.pathMapping("/oauth/token","/auth/get/token");
        // 定制错误页面
        endpoints.pathMapping("/oauth/error","/auth/error");
    }

    /**
     *  自定义生成 授权码
     * @return
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new RedisAuthenticationCodeServices(connectionFactory);
    }

    /**
     *  自定义生成token令牌
     *  http://rensanning.iteye.com/blog/2386553
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new AuthCustomTokenServiceImpl();
    }

    /**
     *  使用jwt 的方式存储token
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        // 公私钥方式 http://blog.csdn.net/buxiaoxia/article/details/64123823
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory keyStoreKeyFactory =
//                new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
//
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
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }



}

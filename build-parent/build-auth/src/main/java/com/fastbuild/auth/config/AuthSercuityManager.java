package com.fastbuild.auth.config;

import com.fastbuild.auth.service.IAuthUserDetailService;
import com.fastbuild.auth.service.impl.AuthUserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * oauth2 配置管理
 * http://blog.didispace.com/spring-security-oauth2-xjf-1/
 * @auther xinch
 * @create 2018/1/16 13:16
 */
@Configuration
@EnableWebSecurity
public class AuthSercuityManager  extends WebSecurityConfigurerAdapter {

    @Resource(name = "authUserDetailService")
    private IAuthUserDetailService authUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailService).passwordEncoder(new Md5PasswordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("user_1").password("123456").authorities("USER")
//                .and()
//                .withUser("user_2").password("123456").authorities("USER")
//                .and()
//                .withUser("admin").password("admin").authorities("ROLE_ADMIN");
    }

    /**
     * 需要配置这个支持password模式j
     * support password grant type
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // 忽略地址前缀
        web.ignoring().antMatchers("/static/**","/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.formLogin()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .requestMatchers().anyRequest()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll();
        http.headers().frameOptions().disable();
        // @formatter:on


//        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
//                .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/403","/css/**", "/js/**", "/fonts/**").permitAll() // 不设限制，都允许访问
//                .anyRequest()
//                .authenticated()
//                .and().logout().logoutSuccessUrl("/").permitAll()
//                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        ;
    }

//    private Filter ssoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
//        OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
//        githubFilter.setRestTemplate(githubTemplate);
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId());
//        tokenServices.setRestTemplate(githubTemplate);
//        githubFilter.setTokenServices(tokenServices);
//        return githubFilter;
//
//    }
//
//    @Bean
//    public FilterRegistrationBean oauth2ClientFilterRegistration( OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }
//
//    @Bean
//    @ConfigurationProperties("github.client")
//    public AuthorizationCodeResourceDetails github() {
//        return new AuthorizationCodeResourceDetails();
//    }
//
//    @Bean
//    @ConfigurationProperties("github.resource")
//    public ResourceServerProperties githubResource() {
//        return new ResourceServerProperties();
//    }
}

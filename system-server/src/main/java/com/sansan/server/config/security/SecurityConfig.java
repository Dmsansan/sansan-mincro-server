package com.sansan.server.config.security;

import com.sansan.server.config.security.filter.AdminAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  <p> Security 核心配置类 </p>
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户密码校验过滤器
     */
    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

    public SecurityConfig(AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter) {
        this.adminAuthenticationProcessingFilter = adminAuthenticationProcessingFilter;
    }

    @Autowired
    private CustomerLogoutSuccessHandler customerLogoutSuccessHandler;

    /**
     * 权限配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();

        // 禁用CSRF 开启跨域
        http.csrf().disable().cors();

        // swagger配置
        registry.antMatchers("/api/v1/login").permitAll()
                .antMatchers("/swagger*//**").permitAll()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html"
                ).permitAll();
        registry.antMatchers("/login", "/index", "/mrUser/logout").permitAll().and().logout().logoutSuccessHandler(customerLogoutSuccessHandler);
        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();
        registry.and().rememberMe();
        registry.anyRequest().authenticated();
        registry.and().headers().frameOptions().disable();

        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 忽略拦截url或静态资源文件夹
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }

}


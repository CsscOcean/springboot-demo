package demo.system.security.config;

import demo.system.security.constant.SecurityConfigConstants;
import demo.system.security.filter.MyUsernamePasswordAuthenticationFilter;
import demo.system.security.handler.MyAccessDeniedHandler;
import demo.system.security.handler.MyAuthenticationFailureHandler;
import demo.system.security.handler.MyAuthenticationSuccessHandler;
import demo.system.security.handler.MyLogoutSuccessHandler;
import demo.system.security.manager.MyAccessDecisionManager;
import demo.system.security.manager.MyFilterInvocationSecurityMetadataSource;
import demo.system.security.strategy.MyAuthenticationEntryPoint;
import demo.system.security.strategy.MyInvalidSessionStrategy;
import demo.system.security.strategy.MySessionInformationExpiredStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * Security 配置类
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private MyAccessDecisionManager myAccessDecisionManager;
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private MyInvalidSessionStrategy myInvalidSessionStrategy;
    @Resource
    private MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy;
    @Resource
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(SecurityConfigConstants.SWAGGER_WHITELIST)
                .antMatchers(SecurityConfigConstants.DRUID_WHITELIST)
                .antMatchers(SecurityConfigConstants.SYSTEM_WHITELIST);
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域资源共享
        http.cors();
        // 关闭CSRF跨站保护
        http.csrf().disable();

        // 决定哪些接口开启防护，哪些接口绕过防护
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(myAccessDecisionManager);
                        return object;
                    }
                })
                // 允许前端跨域联调
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 其它所有接口需要认证才能访问
                .anyRequest().authenticated();

        // 用户登出配置 (成功处理)
        http.logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("JSESSIONID");

        // 指定认证错误处理器
        http.exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and().exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        // Session
        http.sessionManagement()
                .invalidSessionStrategy(myInvalidSessionStrategy)
                // 设置最大允许登录数
                .maximumSessions(1)
                // 当达到最大允许登录数后是否阻止登录
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(mySessionInformationExpiredStrategy);

        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return myUsernamePasswordAuthenticationFilter;
    }
}

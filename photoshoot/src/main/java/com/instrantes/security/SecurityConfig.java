package com.instrantes.security;

import com.instrantes.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@ImportResource({"classpath:spring/photoshoot-servlet.xml", "classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-MyBatis.xml"})

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    DataSource dataSource;


//    InMemoryAuthenticationProvider

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(5));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//关闭防跨站伪请求攻击，默认启用
                .authorizeRequests()//该方法所返回的对象的方法来配置请求级别的安全细节
                .antMatchers("/templates/photoshoot_default.html").permitAll()//对于登录路径不进行拦截
                .antMatchers("/templates/fileupload.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/templates/search.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/show").authenticated()//authenticated()表示允许过的用户访问
                .and()//配置登录页面
                .formLogin().loginPage("/templates/photoshoot_default.html")//登录页面的访问路径
                .loginProcessingUrl("/check")//登录页面下表单提交的路径
                .usernameParameter("account").passwordParameter("password")
//                .successHandler(new AjaxLoginSuccessHandler())
//                .failureHandler(new AjaxLoginFailureHandler()).and()
                .failureUrl("/templates/failure.html")//登录失败后跳转的路径
                .defaultSuccessUrl("/templates/search.html")//登录成功后默认跳转的路径
                .and()
                .logout()//用户退出操作
                .logoutUrl("/logout")//用户退出所访问的路径，需要使用Post方式
                .permitAll()
//                .logoutSuccessUrl("/login?logout=true")
                .and()
                .authorizeRequests()
//                    //定义路径保护的配置方法
//                         .antMatchers(HttpMethod.GET,"/admin")
//                        .authenticated()
                .antMatchers(HttpMethod.GET, "/message/**", "/object/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .rememberMe()//启用记住我功能
//                .alwaysRemember(true)
                .tokenValiditySeconds(86400);
    }


    //配置Spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //        忽略任何以”/resources/”开头的请求
        web.ignoring()
                .antMatchers("/resources/**");

    }


    //    这里需要提供UserDetailsService的原因是RememberMeServices需要用到
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
//        auth.userDetailsService(userDetailsService);      //去掉该处，已用自定义的daoAuthenticationProvider
    }

}



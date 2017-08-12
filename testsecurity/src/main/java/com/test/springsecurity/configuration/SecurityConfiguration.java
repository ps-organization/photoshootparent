package com.test.springsecurity.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;



@Configuration
@EnableWebSecurity
@ComponentScan("com.test.service")
@ImportResource( {"classpath:spring/applicationContext.xml"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

//    @Bean(destroyMethod="close")
//    public DataSource dataSource() {
//        BasicDataSource dataSource=new BasicDataSource();
//        try {dataSource.(env.getProperty("c3p0.driverClass"));} catch (PropertyVetoException e) {e.printStackTrace();}
//        dataSource.setJdbcUrl(env.getProperty("c3p0.jdbcUrl"));
//        dataSource.setUser(env.getProperty("c3p0.user"));
//        dataSource.setPassword(env.getProperty("c3p0.password"));
//        dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("c3p0.initialPoolSize")));
//        dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("c3p0.acquireIncrement")));
//        dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("c3p0.minPoolSize")));
//        dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("c3p0.maxPoolSize")));
//        dataSource.setMaxIdleTime(Integer.valueOf(env.getProperty("c3p0.maxIdleTime")));
//        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(env.getProperty("c3p0.idleConnectionTestPeriod")));
//        return dataSource;
//    }
//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
//
//    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER").and()
                .withUser("admin").password("root123").roles("ADMIN").and()
                .withUser("dba").password("root123").roles("ADMIN", "DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()   //配置请求级别的安全性细节
                .antMatchers("/index*").access("hasRole('ADMIN')")
                .antMatchers("/admin*").access("hasRole('ADMIN') and hasRole('DBA')")  //此处需要两个权限
                .and().formLogin().loginPage("/login.jsp")
                .loginProcessingUrl("/index.jsp")//登录页面下表单提交的路径
                .usernameParameter("account").passwordParameter("password")
                .and()
                .csrf().disable()
//                .and().csrf()
                .exceptionHandling().accessDeniedPage("/Access_Denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //        忽略任何以”/resources/”开头的请求
        web.ignoring()
                .antMatchers("/resources/**");

    }
}

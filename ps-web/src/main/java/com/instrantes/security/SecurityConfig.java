package com.instrantes.security;

import com.instrantes.service.UserDetailsServiceImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ImportResource({"classpath:spring/photoshoot-servlet.xml", "classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-MyBatis.xml"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // CAS单点登录服务地址
    private String SSO_URL = "http://localhost:8080";
//    private String SSO_URL = "http://sso.xxx.com";

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

//    @Autowired
//    DataSource dataSource;
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

    /**
     * 通过重载，配置通过拦截器保护请求
     *
     * @date 2017/11/14
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/error/403") //访问被拒绝页面的URL
                .authenticationEntryPoint(getCasAuthenticationEntryPoint())
                .and()
                .csrf().disable()//关闭防跨站伪请求攻击，默认启用
                .authorizeRequests()//该方法所返回的对象的方法来配置请求级别的安全细节
                .antMatchers("/templates/photoshoot_default.html").permitAll()//对于登录路径不进行拦截
                .antMatchers("/templates/user_default.html").hasAuthority("ROLE_ORDIN")
                .antMatchers("/templates/fileupload.html").hasAuthority("ROLE_ORDIN")
                .antMatchers("/templates/search.html").hasAuthority("ROLE_ORDIN")
                .antMatchers("/templates/upload_picture.html").hasAuthority("ROLE_ORDIN")
                .antMatchers("/templates/picShow.html").hasAuthority("ROLE_ORDIN")
                .antMatchers("/show").authenticated()//authenticated()表示允许过的用户访问
                .and()//配置登录页面
                .formLogin().loginPage("/templates/photoshoot_default.html")//登录页面的访问路径
                .loginProcessingUrl("/PsUserController/check*")//登录页面下表单提交的路径
                .usernameParameter("account").passwordParameter("password")
//                .successHandler(new AjaxLoginSuccessHandler())
//                .failureHandler(new AjaxLoginFailureHandler()).and()
                .failureUrl("/templates/failure.html")//登录失败后跳转的路径
                .defaultSuccessUrl("/PsUserController/initUser")//登录成功后默认跳转的路径
                .and()
                //此处已经写在controller里面，因此不用这个内容
//                .logout()//用户退出操作
//                .logoutUrl("/PsUserController/logout").permitAll()//用户退出所访问的路径，需要使用Post方式,LogoutFilter拦截的路径
////                .logoutSuccessUrl("/templates/photoshoot_default.html")//用户退出后显示的界面
//                .and()
                .authorizeRequests()
//                    //定义路径保护的配置方法
//                         .antMatchers(HttpMethod.GET,"/admin")
//                        .authenticated()
                .antMatchers(HttpMethod.GET, "/message/**", "/object/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .rememberMe()//启用记住我功能
//                .alwaysRemember(true)      //开启后会造成UsernameNotFoundException，暂未处理
                .tokenValiditySeconds(86400)
                .rememberMeParameter("remember-me")//登陆时是否激活记住我功能的参数名字，在登陆页面有展示
                .rememberMeCookieName("workspace");//cookies的名字，登陆后可以通过浏览器查看cookies名字;
    }

    /**
     * 配置Spring Security的Filter链
     *
     * @date 2017/11/14
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //        忽略任何以”/resources/”开头的请求
        web.ignoring()
                .antMatchers("/resources/**");

    }

    /**
     * 配置CAS登陆界面
     *
     * @date 2017/11/14
     */
    public CasAuthenticationEntryPoint getCasAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint point = new CasAuthenticationEntryPoint();
        point.setLoginUrl(SSO_URL + "login");
        point.setServiceProperties(serviceProperties());
        return point;
    }

    /**
     * 认证过滤器
     */
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter filter = new SingleSignOutFilter();
        filter.setCasServerUrlPrefix(SSO_URL);
        filter.setIgnoreInitConfiguration(true);
        return filter;
    }

    public LogoutFilter logoutFilter() {
        LogoutFilter filter = new LogoutFilter(SSO_URL + "logout", new SecurityContextLogoutHandler());
        return filter;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setTicketValidator(cas30ServiceTicketValidator());
        provider.setServiceProperties(serviceProperties());
        provider.setKey("an_id_for_this_auth_provider_only");
        provider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper());
        return provider;
    }

    private ServiceProperties serviceProperties() {
        ServiceProperties properties = new ServiceProperties();
        properties.setService("http://localhost:8080/login/cas");
        properties.setSendRenew(false);
        return properties;
    }

    /**
     * 当CAS认证成功时, Spring Security会自动调用此类对用户进行授权
     */
    private UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper() {
        UserDetailsByNameServiceWrapper wrapper = new UserDetailsByNameServiceWrapper();
        wrapper.setUserDetailsService(userDetailsService);
        return wrapper;
    }

    private Cas20ServiceTicketValidator cas30ServiceTicketValidator() {
        Cas20ServiceTicketValidator validator = new Cas20ServiceTicketValidator(SSO_URL);
        return validator;
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

    //Override method authenticationManagerBean in WebSecurityConfigurerAdapter
    //to expose the AuthenticationManager built using configure(AuthenticationManagerBuilder) as a Spring bean:
    //此处我的理解是在xml中配置一个bean,或是开启一个别名为PsUser中Controller里面的AuthenticationManager注入做准备
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}



package com.instrantes.security;

import com.instrantes.redis.RedisConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //配置root上下文,如Jpa数据源等等的配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class, RedisConfig.class};
    }
    //配置dispatcher servlet，如果在root config指定了该转发规则就可以忽略
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
package com.data.masterandslave.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Copyright © JinNuoFeng Network Technology Co.,Ltd.</p>
 * WEB项目配置
 * @author Alan on 2017/10/30.
 */
//@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加自定义拦截器
     * @param registry 资源注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器，不拦截登录接口和静态资源文件夹
        registry.addInterceptor(getInterfaceAuthCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/system/user/verifysys")
                .excludePathPatterns("/system/upload");
        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor getInterfaceAuthCheckInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 配置项目外某个路径为静态资源路径
     * @param registry 资源注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(fileServerProperties.getSecPath() + "/**")
//                .addResourceLocations("file:" + fileServerProperties.getRootPath() + fileServerProperties.getSecPath() +"/");
        super.addResourceHandlers(registry);
    }

}

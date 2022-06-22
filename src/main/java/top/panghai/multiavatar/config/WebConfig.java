package top.panghai.multiavatar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.panghai.multiavatar.interceptor.AddressInterceptor;

/**
 * @Author: panghai
 * @Date: 2022/06/21/23:50
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AddressInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }

    /**
     * 添加视图控制器（访问任何请求路径、跳转到resources下的templates/index.html）
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/*").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

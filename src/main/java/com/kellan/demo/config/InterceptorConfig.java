package com.kellan.demo.config;

import com.kellan.demo.intercaptor.ParamInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加拦截器配置
 *
 * @author Kellan_Song
 * @createTime 2018年3月27日
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器的方式，可直接new一个对象，
     * registry.addInterceptor(new ParamInterceptor())，
     * 但通过手动new出来的拦截器中，无法使用@Autowired 和 @Value 加载对象和配置文件参数。
     * <p>
     * 所以需要在添加拦截器此处，通过@Bean 注解，意味着将这个对象
     * 交给spring管理。那么该拦截器才可以使用@Value等spring管理的注解
     *
     * @return
     */
    @Bean
    public ParamInterceptor paramInterceptor() {
        return new ParamInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(paramInterceptor()).addPathPatterns("/api/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}

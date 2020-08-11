package com.wth.train.congfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* MVCの設定ファイル
* */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //ViewControllersを追加
    public void addViewControllers(ViewControllerRegistry registry){
        //topページの設定
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }
    //インターセプターの設定、登録状態検証
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login.html","/js/**","/login");
    }
}

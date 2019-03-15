package com.github.webshop.conf;

import com.github.webshop.Interceptor.BaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebStaticResourceConfig implements WebMvcConfigurer {

    /**
     * 指定拦截器给资源放行
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //让拦截器放行
//        registry
//                .addInterceptor(getBaseInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * 想自定义静态资源映射目录的话，只需重写addResourceHandlers方法即可。
     * 通过addResourceHandler添加映射路径，然后通过addResourceLocations来指定路径。我们访问自定义my文件夹中
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        放行静态资源
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUplodFilePath()+"upload/");
//        外部url可以直接访问
          registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//        放行static目录下所有资源，外部url可以直接访问
          registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
          registry.addResourceHandler("/layui/static/**").addResourceLocations("classpath:/static/layui/");

    }

    private BaseInterceptor getBaseInterceptor(){
        return new BaseInterceptor();
    }
}

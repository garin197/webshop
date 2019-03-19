package com.github.webshop.conf;

import com.github.webshop.Interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
@PropertySource("upload-config.properties")
public class WebStaticResourceConfig implements WebMvcConfigurer {

    /**
     * 指定拦截器给资源放行
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //让拦截器放行
    }

    @Value("${file.virtualPath}")
    private String virtualPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * 想自定义静态资源映射目录的话，只需重写addResourceHandlers方法即可。
     * 通过addResourceHandler添加映射路径，然后通过addResourceLocations来指定路径。我们访问自定义my文件夹中
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //放行静态资源、外部url可以直接访问、放行static目录下所有资源，外部url可以直接访问
          registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
          registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
          registry.addResourceHandler("/layui/static/**").addResourceLocations("classpath:/static/layui/");
          registry.addResourceHandler(virtualPath).addResourceLocations(uploadFolder);//创建虚拟路径映射

    }

    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }

    private BaseInterceptor getBaseInterceptor(){
        return new BaseInterceptor();
    }
}

package top.thevsk.longsong.reborn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

@Configuration
public class MaterialConfig extends WebMvcConfigurationSupport {

    @Value("${material.local-path}")
    private String localPath;
    @Value("${material.temp-path}")
    private String tempPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("local/**").addResourceLocations("file:" + localPath + File.separator);
        registry.addResourceHandler("temp/**").addResourceLocations("file:" + tempPath + File.separator);
        super.addResourceHandlers(registry);
    }
}
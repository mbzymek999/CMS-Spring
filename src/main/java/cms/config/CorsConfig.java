package cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry myCorsRegistry){
        myCorsRegistry.addMapping("*")
                .allowedOrigins("http://localhost:8001")  //frontend's link
                .allowedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Header", "Access-Control-Expose-Headers", "Content-Range", "Content-Length", "Connection", "Content-Type", "X-Total-Count", "X-Content-Type-Options", "Set-Cookies", "*")
                .allowedMethods("GET", "POST", "PUT", "HEAD", "OPTIONS", "PATCH")
                .allowCredentials(true);

    }

}
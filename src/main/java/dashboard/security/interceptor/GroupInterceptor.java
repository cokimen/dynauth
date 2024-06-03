package dashboard.security.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class GroupInterceptor implements WebMvcConfigurer {

    @Autowired
    FluidInterceptor fluidInterceptor;

    @Autowired
    JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( fluidInterceptor);
        registry.addInterceptor( jwtInterceptor);
    }
}

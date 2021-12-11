package <%=package%>.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import <%=package%>.aop.logging.LoggingAspect;
import <%=package%>.aop.logging.OperateLoggingAspect;
import <%=package%>.constants.SystemConstants;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(SystemConstants.PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }

    @Bean
    public OperateLoggingAspect operateLoggingAspect() {
        return new OperateLoggingAspect();
    }
}

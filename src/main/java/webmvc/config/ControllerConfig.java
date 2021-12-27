package webmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import webmvc.controller.HelloController;

@Configuration
@ComponentScan(basePackages = "webmvc.controller")
public class ControllerConfig {
}

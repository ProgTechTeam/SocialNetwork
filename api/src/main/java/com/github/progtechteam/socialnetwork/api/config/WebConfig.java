package com.github.progtechteam.socialnetwork.api.config;

import com.github.progtechteam.socialnetwork.services.config.ServicesConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Class for configuring web layer.
 *
 * @author Evgenii Puliaev
 */
@Configuration
@ComponentScan(basePackages = "com.github.progtechteam.socialnetwork.api.controller")
@Import({
        OpenApiConfig.class,
        ServicesConfig.class
})
public class WebConfig {

}

package com.github.progtechteam.socialnetwork.services.config;

import com.github.progtechteam.socialnetwork.data.config.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Class for configuring service layer.
 *
 * @author Evgenii Puliaev
 */
@Configuration
@ComponentScan(basePackages = {
        "com.github.progtechteam.socialnetwork.services.mapper",
        "com.github.progtechteam.socialnetwork.services.service.impl",
})
@Import(JpaConfig.class)
public class ServicesConfig {

}

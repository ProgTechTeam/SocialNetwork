package com.github.progtechteam.socialnetwork.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class for configuring Spring JPA.
 *
 * @author Evgenii Puliaev
 */
@Configuration
@EntityScan({
        "com.github.progtechteam.socialnetwork.data.entity",
        "com.github.progtechteam.socialnetwork.data.converter"
})
@EnableJpaRepositories(basePackages = {"com.github.progtechteam.socialnetwork.data.repository"})
public class JpaConfig {

}

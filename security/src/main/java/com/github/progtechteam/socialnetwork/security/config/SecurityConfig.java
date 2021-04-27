package com.github.progtechteam.socialnetwork.security.config;

import com.github.progtechteam.socialnetwork.commons.Role;
import com.github.progtechteam.socialnetwork.commons.Url;
import com.github.progtechteam.socialnetwork.security.handler.SnAccessDeniedHandler;
import com.github.progtechteam.socialnetwork.security.provider.DaoAuthProvider;
import com.github.progtechteam.socialnetwork.security.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Class for configuring Spring Security.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {
        "com.github.progtechteam.socialnetwork.security.service",
        "com.github.progtechteam.socialnetwork.security.handler",
        "com.github.progtechteam.socialnetwork.security.provider"
})
@Import({CurrentUserConfig.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SnAccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authEntryPoint;
    private final AuthenticationSuccessHandler authSuccessHandler;
    private final AuthenticationFailureHandler authFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final UserDetailsService userDetailsService;
    private final CurrentUserService currentUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        configuration.setAllowedMethods(List.of(
                HttpMethod.POST.name(),
                HttpMethod.GET.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name()
        ));
        configuration.setMaxAge(3600L);
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(Url.WILDCARD, configuration);
        return source;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        final var daoAuthenticationProvider = new DaoAuthProvider(currentUserService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .configurationSource(corsConfigurationSource())
                .and().csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(Url.PUBLIC + Url.WILDCARD).permitAll()
                    .antMatchers(Url.SWAGGER_UI + Url.WILDCARD).permitAll()
                    .antMatchers(Url.API_DOCS + Url.WILDCARD).permitAll()
                    .antMatchers(Url.MODERATOR + Url.WILDCARD).hasAuthority(Role.MODERATOR.getSystemName())
                    .antMatchers(Url.AUTH + Url.WILDCARD).not().authenticated()
                    .anyRequest().authenticated()
                .and().formLogin()
                    .loginProcessingUrl(Url.LOGIN)
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailureHandler)
                .and().logout()
                    .logoutUrl(Url.LOGOUT)
                    .logoutSuccessHandler(logoutSuccessHandler)
                .and().exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and().sessionManagement()
                    .sessionFixation()
                    .changeSessionId();
    }
}

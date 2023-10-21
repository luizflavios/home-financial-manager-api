package br.com.api.core.security;

import br.com.api.service.AuthenticationModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurity {

    private final AuthEntryPoint authEntryPoint;
    private final ApiFilter apiFilter;
    private final AuthenticationModelService authenticationModelService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurity(AuthEntryPoint authEntryPoint, ApiFilter apiFilter, AuthenticationModelService authenticationModelService, PasswordEncoder passwordEncoder) {
        this.authEntryPoint = authEntryPoint;
        this.apiFilter = apiFilter;
        this.authenticationModelService = authenticationModelService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(this.corsConfiguration()))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(STATELESS))
                .addFilterAt(apiFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(this.authenticationProvider())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {

                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/users/**").permitAll();

                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            "/health/**",
                            "/auth/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/swagger-resources/**"
                    ).permitAll();

                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(this.authEntryPoint));

        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfiguration() {
        var corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(singletonList("*"));
        corsConfiguration.setAllowedMethods(singletonList("*"));
        corsConfiguration.setAllowedHeaders(singletonList("*"));

        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(this.passwordEncoder);
        authProvider.setUserDetailsService(this.authenticationModelService);
        return authProvider;
    }

}

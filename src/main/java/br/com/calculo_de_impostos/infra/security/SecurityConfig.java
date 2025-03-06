package br.com.calculo_de_impostos.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, "/tipos").hasRole("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cálculo").hasRole("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tipos/id").hasRole("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuários/registrar").hasRole("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuários/login").hasRole("ROLE_ADMIN")
                        .anyRequest().authenticated()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

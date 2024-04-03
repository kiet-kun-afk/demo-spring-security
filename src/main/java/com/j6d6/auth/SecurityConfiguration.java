package com.j6d6.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
                return authConfig.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)
                        throws Exception {
                http.csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req
                                                .requestMatchers("/home/admin")
                                                .hasRole("ADMIN")
                                                .requestMatchers("/home/user")
                                                .hasRole("USER")
                                                .requestMatchers("/home/auth")
                                                .hasAnyRole("USER", "ADMIN")
                                                .requestMatchers("/home/auth", "/home/user", "/home/admin")
                                                .authenticated()
                                                .anyRequest()
                                                .permitAll())
                                .formLogin(form -> form
                                                .loginPage("/login").permitAll()
                                                .defaultSuccessUrl("/home/index", false))
                                // .rememberMe().and()
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login"))
                                .exceptionHandling(e -> e
                                                .accessDeniedPage("/accessDenied"));
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
                        throws Exception {
                auth.inMemoryAuthentication()
                                .withUser("user")
                                .password(passwordEncoder.encode("123"))
                                .roles("USER")
                                .and()
                                .withUser("admin")
                                .password(passwordEncoder.encode("123"))
                                .roles("ADMIN")
                                .and()
                                .withUser("boss")
                                .password(passwordEncoder.encode("123"))
                                .roles("USER", "ADMIN")
                                .and()
                                .withUser("guest")
                                .password(passwordEncoder.encode("123"))
                                .roles("").authorities("GUEST");
        }
}
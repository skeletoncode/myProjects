package com.tech.tech_servise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@EnableWebSecurity // обязательная аанатоция для работы с библиотекой security
@EnableMethodSecurity(securedEnabled = true) // для работы с ролями
@Configuration
public class SecurityConfiguration {
    private final UserDetailsManager userDetailsManager;
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsManager userDetailsManager, UserDetailsService userDetailsService) {
        this.userDetailsManager = userDetailsManager;
        this.userDetailsService = userDetailsService;
    }
    /**
     * отвечает за извлечение пользователя: из БД, файла и т.д
     * Пользователь, полученный из БД сравнивается с пользователем из формы или токена
     */


    /**
     * шифрование паролей
     * проверка соответствия зашиврованного пароля и пароля в чистом виде
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationProvider - провайдер, которому делегируеся процесс аутентификации
     * DaoAuthenticationProvider - реализация AuthenticationProvider, занимающаяся аутентификацией
     */

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/account/registration", "/account/login")
                        .not().authenticated()
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .usernameParameter("application_user_username")
                        .passwordParameter("application_user_password")
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login")
                        .failureUrl("/account/login?failed")
                        .defaultSuccessUrl("/account")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/account/logout")
                        .logoutSuccessUrl("/account/login")
                        .permitAll())
                .build();

    }

}

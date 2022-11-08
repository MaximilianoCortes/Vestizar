package com.example.vestizar.config;

import com.example.vestizar.servicio.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    /**
     * Configuracion de autorizaciones para la pagina web como permitir el acceso a ciertos archivos/directorios correspondientes al MVC del proyecto
     * @param http Protocolo http de la pagina web
     * @return Retorna la build de la configuracion realizada en el protoco http de la pagina.
     * @throws Exception Arroja un error para poder manejarlo en otro contexto
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(consulta -> consulta
                        .mvcMatchers("/css/**","/img/**","/", "/index","/registro","/crearUsuario","/guardarUsuario","/iniciadoSesion").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin((formulario) -> formulario
                        .loginPage("/index")
                        .permitAll()
                )
                .logout((cerrarSesion) -> cerrarSesion.permitAll())
                .build();
    }

    /**
     * Codificador de contrasena
     * @return Retorna una instancia de codificador para poder usar en otros contextos
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
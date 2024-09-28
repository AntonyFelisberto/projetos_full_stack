package com.projetooficina.sistemaoficinaback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.projetooficina.sistemaoficinaback.security.JWTAuthenticarFilter;
import com.projetooficina.sistemaoficinaback.security.JWTValidarFilter;
import com.projetooficina.sistemaoficinaback.services.DetalheUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private static final String[] SECURE_PATTERNS = {"/h2-console/**"};

    private final Environment environment;

    private final DetalheUsuarioServiceImpl detalheUsuarioService;

    private final PasswordEncoder passwordEncoder;

    public WebSecurity(DetalheUsuarioServiceImpl detalheUsuarioService, PasswordEncoder passwordEncoder, Environment environment) {
        this.detalheUsuarioService = detalheUsuarioService;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalheUsuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(environment.getActiveProfiles()).contains("antony") ||
                Arrays.asList(environment.getActiveProfiles()).contains("bruno")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.addFilter(new JWTAuthenticarFilter(authenticationManager()));
        http.addFilter(new JWTValidarFilter(authenticationManager()));
        http.authorizeRequests().antMatchers(SECURE_PATTERNS).permitAll().anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();

        //CASO ATIVE ESSES SET ALLOWEDS NO PRIMEIRO TERA QUE COLOCAR TRUE PARA DIZER QUE SÓ UM LINK ACESSARA A APLICAÇÃO
        //NO OUTRO SET ALLOWED COLOCARA OS LINKS DAS APLICAÇÕES QUE TERÃO ACESSO AO BACK
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}

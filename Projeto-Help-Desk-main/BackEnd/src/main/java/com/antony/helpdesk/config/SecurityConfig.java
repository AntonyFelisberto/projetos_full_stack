package com.antony.helpdesk.config;

import com.antony.helpdesk.security.JWTAuthenticationFilter;
import com.antony.helpdesk.security.JWTAuthorizationFilter;
import com.antony.helpdesk.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] SECURE_PATTERNS = {"/h2-console/**"};

    @Autowired
    private Environment environment;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(environment.getActiveProfiles()).contains("dev") ||
                Arrays.asList(environment.getActiveProfiles()).contains("hom")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtUtil,userDetailsService));
        http.authorizeRequests().antMatchers(SECURE_PATTERNS).permitAll().anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}



//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig  {
//
//    private static final String[] SECURE_PATTERNS = {"/h2-console/**"};
//
//    @Autowired
//    private Environment environment;
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        if(Arrays.asList(environment.getActiveProfiles()).contains("dev") ||
//                Arrays.asList(environment.getActiveProfiles()).contains("hom")){
//            http.headers().frameOptions().disable();
//        }
//
//        http.cors().and().csrf().disable();
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtUtil));
//        http.authorizeRequests().antMatchers(SECURE_PATTERNS).permitAll().anyRequest().authenticated();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**",configuration);
//        return source;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}

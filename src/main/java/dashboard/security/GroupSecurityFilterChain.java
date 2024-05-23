package dashboard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class GroupSecurityFilterChain {


    public static Logger logger = LoggerFactory.getLogger(GroupSecurityFilterChain.class);

    @Autowired
    public GroupUserDetailsService groupUserDetailsService;




    @Bean
    public PasswordEncoder getPasswordEncoderBcrypt() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(groupUserDetailsService.userDetailsService1());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                ).authenticationManager(authenticationManager)
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(groupUserDetailsService.userDetailsService2());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .securityMatcher("/webapp/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                ).authenticationManager(authenticationManager)

                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    @Order(3)
    SecurityFilterChain securityFilterChain3(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(groupUserDetailsService.userDetailsService3());


        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .securityMatcher("/joiner/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                ).authenticationManager(authenticationManager)

                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    @Order(4)
    SecurityFilterChain securityFilterChainByJDBC(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(groupUserDetailsService.jdbcUserFilterChain());


        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .securityMatcher("/myjdbc/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                ).authenticationManager(authenticationManager)

                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    @Order(4)
    SecurityFilterChain freeFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/**").permitAll();
                            auth.requestMatchers("/password/**").permitAll();
                            auth.requestMatchers("/error").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .build();
    }

}

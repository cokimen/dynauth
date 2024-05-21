package dashboard.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Configuration
public class GroupUserDetailsService {
    @Bean
    public UserDetailsService userDetailsService1() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user1").password("password").roles("USER").build());
        manager.createUser(users.username("admin1").password("password").roles("USER", "ADMIN").build());
        return manager;
    }


    @Bean
    public UserDetailsService userDetailsService2() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        return new InMemoryUserDetailsManager(
                users.username("user2").password("password").roles("USER").build(),
                users.username("admin2").password("password").roles("USER", "ADMIN").build()
        );
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService3() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserDetails user = User.withUsername("user3")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin3")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }



}

package dashboard.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class GroupUserDetailsService {

    public final static Logger logger = LoggerFactory.getLogger( GroupUserDetailsService.class);

    @Autowired
    private DataSource datasource;

    @Bean
    public UserDetailsService userDetailsService1() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder().passwordEncoder( x -> {
            return (new BCryptPasswordEncoder()).encode(x);
        });
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user1").password("password").roles("USER").build());
        manager.createUser(users.username("admin1").password("password").roles("USER", "ADMIN").build());
        return manager;
    }


    @Bean
    public UserDetailsService userDetailsService2() throws Exception {
        // ensure the passwords are encoded properly
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User.UserBuilder users = User.builder().passwordEncoder(x -> {
            return passwordEncoder.encode(x);
        });
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        logger.info("Oke  "+passwordEncoder.encode("password"));;


        return new InMemoryUserDetailsManager(
                users.username("user2").password("cokimen1").roles("USER").build(),
                users.username("admin2").password("cokimen1").roles("USER", "ADMIN").build()
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


    @Bean
    public UserDetailsService jdbcUserFilterChain() throws Exception {

        System.out.println("Invoked");

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(
                datasource
        );

        jdbcUserDetailsManager.setUsersByUsernameQuery("select usercode,passcode,status_active "
                + "from mst_user "
                + "where usercode = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT usercode, authority\n" +
                "FROM mst_user   lEFT JOIN mst_grouprole_to_role \n" +
                "ON usercode = ref_grouprolecode WHERE usercode = ?");
        jdbcUserDetailsManager.setRolePrefix("");

        logger.info("OKEEEE");
        return jdbcUserDetailsManager;
    }




}

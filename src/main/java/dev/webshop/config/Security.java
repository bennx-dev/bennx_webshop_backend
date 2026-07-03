package dev.webshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Security {

    private final DataSource dataSource;

    public Security(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    UserDetailsService userDetailsService() {

        var manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(
                """
                        select emailadres as username
                        , paswoord as password
                        , case when disabled = 0 then true else false end as enabled
                        from GebruikersAccounts
                        where emailadres = ?
                        """);

        manager.setAuthoritiesByUsernameQuery(
                """ 
                        select emailadres as username
                        , 'ROLE_USER' as authorities
                        from GebruikersAccounts
                        where emailadres = ?
                        """);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(c -> c
                        .requestMatchers("/login"
                        ,"/api/categorieen/**"
                        ,"/api/artikelen/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());


        return http.build();
    }
}

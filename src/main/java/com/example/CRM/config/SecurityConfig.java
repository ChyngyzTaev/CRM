package com.example.CRM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/general-record/get-chart-by-id/{id}").hasRole("ADMIN")
                .antMatchers("/general-record/**").hasRole("ADMIN")
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/api/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/trainer/**").hasAnyRole("ADMIN", "MANAGER", "TRAINER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "MANAGER", "TRAINER", "CLIENT")
                .antMatchers("/api/role/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/list-exercises/**").hasAnyRole("ADMIN", "TRAINER", "CLIENT")
                .antMatchers("/api/subscription/**").hasAnyRole("ADMIN", "MANAGER","CLIENT")
                .antMatchers("/api/chart/**").hasAnyRole("ADMIN", "TRAINER", "CLIENT")
                .antMatchers("/api/auth/login").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, is_active from users where username = ?")
                .authoritiesByUsernameQuery("select u.username, ur.role_name as role from user_role ur inner join " +
                        "users u on ur.user_id = u.id where u.username = ? and u.is_active = 1");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.example.CRM.security;

import com.example.CRM.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/user/login").permitAll()

                .antMatchers(HttpMethod.POST, "/api/user/add-new-user").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user/get-user-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user/get-all-users").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user/get-user-by-userName").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/delete-user-by-id/{id}").permitAll()


                .antMatchers(HttpMethod.POST, "/api/role/add-new-role").permitAll()
                .antMatchers(HttpMethod.GET,"/api/role/get-role-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/role/get-role-by-roleName").permitAll()


                .antMatchers(HttpMethod.POST, "/api/chart/add-new-chart").permitAll()
                .antMatchers(HttpMethod.GET, "/api/chart/get-chart-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/chart/get-all-chart").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/chart/delete-chart-by-id/{id}").permitAll()


                .antMatchers(HttpMethod.POST, "/api/subscription/add-new-subscription").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/subscription/delete-subscription-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/subscription/update-subscription").permitAll()


                .antMatchers(HttpMethod.POST, "/api/list-exercises/add-new-list-exercises").permitAll()
                .antMatchers(HttpMethod.GET, "/api/list-exercises/get-list-exercises-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/list-exercises/get-all-list-exercises").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/list-exercises/delete-list-exercises-by-id/{id}").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

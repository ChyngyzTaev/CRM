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
                .antMatchers(HttpMethod.GET,"/api/user/get-user-by-id/{id}").hasAnyRole("Admin", "Manager")
                .antMatchers(HttpMethod.GET,"/api/user/get-all-users").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/api/user/delete-user-by-id/{id}").hasRole("Admin")

                .antMatchers(HttpMethod.GET, "/api/trainer/get-trainer-by-id/{id}").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/api/trainer/get-all-trainer").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/api/trainer/delete-trainer-by-id/{id}").hasRole("Admin")



//
//                .antMatchers(HttpMethod.POST, "/api/add-new-user").hasRole("Manager")
//                .antMatchers(HttpMethod.GET,"/api/user/get-user-by-id/{id}").hasRole("Manager")
//                .antMatchers(HttpMethod.GET,"/api/user/get-all-users").hasRole("Manager")
//                .antMatchers(HttpMethod.DELETE, "/api/user/delete-user-by-id/{id}").hasRole("Manager")

                .antMatchers(HttpMethod.POST, "/api/trainer/add-new-trainer").hasRole("Manager")
                .antMatchers(HttpMethod.GET, "/api/trainer/get-trainer-by-id/{id}").hasRole("Manager")
                .antMatchers(HttpMethod.GET, "/api/trainer/get-all-trainer").hasRole("Manager")
                .antMatchers(HttpMethod.DELETE, "/api/trainer/delete-trainer-by-id/{id}").hasRole("Manager")

                .antMatchers(HttpMethod.POST, "/api/subscription/add-new-subscription").hasRole("Manager")
                .antMatchers(HttpMethod.DELETE, "/api/subscription/delete-subscription-by-id/{id}").hasRole("Manager")
                .antMatchers(HttpMethod.PUT, "/api/subscription/update-subscription").hasRole("Manager")

                .antMatchers(HttpMethod.POST, "/api/user-information/add-new-user-info").hasRole("Manager")
                .antMatchers(HttpMethod.GET, "/api/user-information/get-user-info/{id}").hasRole("Manager")




                .antMatchers(HttpMethod.POST, "/api/chart/add-new-chart").hasRole("Trainer")
                .antMatchers(HttpMethod.GET, "/api/chart/get-chart-by-id/{id}").hasRole("Trainer")
                .antMatchers(HttpMethod.GET, "/api/chart/get-all-chart").hasRole("Trainer")
                .antMatchers(HttpMethod.DELETE, "/api/chart/delete-chart-by-id/{id}").hasRole("Trainer")

                .antMatchers(HttpMethod.POST, "/api/list-exercises/add-new-list-exercises").hasRole("Trainer")
                .antMatchers(HttpMethod.GET, "/api/list-exercises/get-list-exercises-by-id/{id}").hasRole("Trainer")
                .antMatchers(HttpMethod.GET, "/api/list-exercises/get-all-list-exercises").hasRole("Trainer")
                .antMatchers(HttpMethod.DELETE, "/api/list-exercises/delete-list-exercises-by-id/{id}").hasRole("Trainer")





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

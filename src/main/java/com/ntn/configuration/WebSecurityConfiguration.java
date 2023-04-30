package com.ntn.configuration;

import com.ntn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthEntryPointJwt entryPointJwt;

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter createAuthTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/products/**").permitAll()
//                .antMatchers("/api/v1/users/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/api/v1/users/**").permitAll()
                .antMatchers("/api/v1/users/*").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers("/api/v1/users/admin/**").hasAnyAuthority("ADMIN")

                .antMatchers("/api/v1/products/update").hasAnyAuthority("MANAGER")


                .antMatchers("/api/v1/carts/**").hasAnyAuthority("CUSTOMER", "MANAGER", "ADMIN")
                .antMatchers("/api/v1/categories/**").permitAll()
                .antMatchers("/api/v1/orders/**").hasAnyAuthority("CUSTOMER", "MANAGER", "ADMIN")
                .antMatchers("/api/v1/order-details/**").hasAnyAuthority("CUSTOMER", "MANAGER", "ADMIN")
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/shipping/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPointJwt)
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        http.addFilterBefore(createAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}

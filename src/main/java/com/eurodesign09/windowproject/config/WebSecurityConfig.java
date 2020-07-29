package com.eurodesign09.windowproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/")
        .antMatchers("/save_callMeForm")
        .antMatchers("/save");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/save_workDone").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete_workDone/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/edit_callMeForm/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete_callMeForm/**").hasAuthority("ADMIN")
                .antMatchers("/admin/publish_approvedFeedback/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete_approvedFeedback/**").hasAuthority("ADMIN")
                .antMatchers("/admin/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete/**").hasAuthority("ADMIN")
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/")
                .failureUrl("/login_failure")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/admin/403");
    }


}

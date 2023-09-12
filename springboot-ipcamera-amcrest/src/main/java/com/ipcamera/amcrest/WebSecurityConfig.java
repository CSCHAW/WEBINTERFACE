package com.ipcamera.amcrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // .antMatchers("/","/css/**", "/js/**","/resources/**","/image/**","/css/login/files/**","/css/login/files/**","/**/*.js", "/**/*.css","/js/login/files/**","/css/live/files/**").permitAll()

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

            .authorizeRequests()
                //.antMatchers("/","/css/**", "/js/**","/resources/**","/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/live",true)
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                //.invalidateHttpSession(true)
                //.deleteCookies("JSESSIONID")
                .permitAll();
                //.and()
                //.csrf()
                //.disable()
                //.cors().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
               .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/image/**", "/icon/**","/css/**", "/js/**","/resources/**","/image/**","../static/css/login/files/**","/css/login/files/**","/**/*.js", "/**/*.css","/**/css/login/files/*.css","/js/login/files/**","/css/live/files/**","/video/**");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
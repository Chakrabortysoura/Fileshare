package com.home_server.fileshare.Security;

import com.home_server.fileshare.Service.UserConfigurationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    UserConfigurationHelper dataReader;

    @Bean
    public PasswordEncoder customPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http    .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/home").authenticated()
                        .requestMatchers("/share/**").authenticated()
                        .anyRequest().permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService usercredentials(){
        String username= "";
        String password= "";
        try{
            username=dataReader.getusername();
            password=dataReader.getpassword();
        }catch (IOException e){
            System.out.println("There was some error accessing the file");
        }
        if(username==null || password==null){
            username="admin";
            password=customPasswordEncoder().encode("admin");
        }
        UserDetails user= User.builder()
                .username(username)
                .password(password)
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}

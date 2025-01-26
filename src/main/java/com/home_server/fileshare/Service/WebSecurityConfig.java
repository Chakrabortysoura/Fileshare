package com.home_server.fileshare.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
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

    UserDataReader dataReader=new UserDataReader();

    @Bean
    public PasswordEncoder custompasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityconfig(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests)->requests
                        .requestMatchers("/share/download/**").permitAll() // making all the download pages to be freely accessible
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService usercredentials(){
        String username=new String();
        String password=new String();
        try{
            username=dataReader.getusername();
            password=dataReader.getpassword();
        }catch (IOException e){
            System.out.println("There was some error accessing the file");
        }
        if(username==null || password==null){
            username="admin";
            password="$2a$10$NfijqLDGqlVprqxlfYtb0uxreMS86xQOXOgiUjMNI/.rC7dze7K6.";
        }
        else if(username.equals("")|| password.equals("")){
            username="admin";
            password="$2a$10$NfijqLDGqlVprqxlfYtb0uxreMS86xQOXOgiUjMNI/.rC7dze7K6.";
        }
        UserDetails user= User.builder()
                .username(username)
                .password(password)
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}

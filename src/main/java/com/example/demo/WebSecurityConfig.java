package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity   //webSpring Security를 사용 하겠다.
public class WebSecurityConfig {

    /**
     * 승인이 된 url path를 접근 할 수 있다.
     * 기본적인 security setting이 날아 간다.
     *
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((requests) ->
                requests.antMatchers("/","/home").permitAll()  //특정 패턴에 해당 하는
                        .anyRequest().authenticated()) //로그인 한 사용자들만 볼 수 있게 설정
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) ->logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user=
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}

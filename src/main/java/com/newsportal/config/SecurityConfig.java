package com.newsportal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().
//                disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }


//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").permitAll();
//    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/create-news/").hasRole("ADMIN")
//                .antMatchers("/update-news/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/access-denied");
//    }




}

















//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
////                .antMatchers(HttpMethod.GET, urlMain).hasAnyRole(Role.ADMIN.name(), Role.USER.name())
////                .antMatchers(HttpMethod.POST, urlMain).hasAnyRole(Role.ADMIN.name())
////                .antMatchers(HttpMethod.DELETE, urlMain).hasAnyRole(Role.ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//    }
//
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService() {
////        return new InMemoryUserDetailsManager(
////                User.builder()
////                        .username("admin")
////                        .password(passwordEncoder().encode("admin"))
////                        .roles(Role.ADMIN.name())
////                        .build(),
////                User.builder()
////                .username("user")
////                .password(passwordEncoder().encode("user"))
////                .roles(Role.USER.name())
////                .build()
////        );
////    }
//
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//}

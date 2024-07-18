package com.kanchan.Mvc.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomerUserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(CustomerUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login","/events/{eventId}/edit","/events/{eventId}", "/register","/events/{clubId}/new","/clubs/{clubId}/delete","/clubs/search","/register/save","/clubs/{clubId}","/clubs/","/events","/clubs/{clubId}/edit","/css/**","/js/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> {
                    try {
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/clubs")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true")
                                .permitAll()
                                .and()
                                .logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );
//                .logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
//                )
// ;

        return http.build();
    }
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}

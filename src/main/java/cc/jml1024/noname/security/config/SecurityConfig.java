package cc.jml1024.noname.security.config;

import cc.jml1024.noname.security.filter.CaptchaAuthenticationFilter;
import cc.jml1024.noname.security.serivce.SysUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author evil
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private SysUserDetailsServiceImpl sysUserDetailsServiceImpl;

    @Autowired
    private CaptchaAuthenticationFilter captchaAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(sysUserDetailsServiceImpl)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
        return authenticationManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/assets/**", "/captcha/image", "/error")
                .permitAll().anyRequest().authenticated();
        http.addFilterBefore(captchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/controlPanel")
                .failureUrl("/login?error=true");
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll();
        http.csrf().disable();
        return http.build();
    }
}

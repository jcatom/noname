package cc.jml1024.noname.security.config;

import cc.jml1024.noname.security.filter.CaptchaAuthenticationFilter;
import cc.jml1024.noname.security.filter.UsernameAndPasswordVerifyFilter;
import cc.jml1024.noname.security.serivce.SysUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author evil
 */
@Configuration
public class SecurityConfig {

    private SysUserDetailsServiceImpl sysUserDetailsServiceImpl;

    private CaptchaAuthenticationFilter captchaAuthenticationFilter;

    private UsernameAndPasswordVerifyFilter usernameAndPasswordVerifyFilter;

    public SecurityConfig(SysUserDetailsServiceImpl sysUserDetailsServiceImpl, CaptchaAuthenticationFilter captchaAuthenticationFilter, UsernameAndPasswordVerifyFilter usernameAndPasswordVerifyFilter) {
        this.sysUserDetailsServiceImpl = sysUserDetailsServiceImpl;
        this.captchaAuthenticationFilter = captchaAuthenticationFilter;
        this.usernameAndPasswordVerifyFilter = usernameAndPasswordVerifyFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
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
        http.addFilterBefore(usernameAndPasswordVerifyFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(captchaAuthenticationFilter, UsernameAndPasswordVerifyFilter.class);
        http.formLogin()
                .loginPage("/login/login").permitAll()
                .defaultSuccessUrl("/controlPanel")
                .failureUrl("/login/login?error=true");
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/login").permitAll();
        http.csrf().disable();
        return http.build();
    }
}

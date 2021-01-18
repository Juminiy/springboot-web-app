package com.hua.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/page/**").hasRole("student")
                .antMatchers("/common/**").permitAll()
                .antMatchers("/employ/**").hasRole("admin");
        http.formLogin().loginPage("/signin")
                .failureUrl("/signin?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login");
        http.csrf().disable();
        http.logout(logout ->
                logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
        );
        //提交name="remember"
        http.rememberMe().rememberMeParameter("remember");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("juminiy@2084team.cn").password(new BCryptPasswordEncoder().encode("2084team")).roles("admin","student")
                .and()
                .withUser("nomanker@2084team.cn").password(new BCryptPasswordEncoder().encode("2084team")).roles("admin","student")
                .and()
                .withUser("ggssh@2084team.cn").password(new BCryptPasswordEncoder().encode("2084team")).roles("admin","student");
    }

}
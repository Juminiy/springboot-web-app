package com.hua.config;


import com.hua.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl ;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/common/**").permitAll()
                .antMatchers("/ajax/**").permitAll()
                .antMatchers("/staff/**").hasRole("staff")
                .antMatchers("/page/**").hasRole("student")
                .antMatchers("/mark/**").hasRole("admin")
                .antMatchers("/employ/**").hasRole("admin");
        http.formLogin().loginPage("/signin")
                .failureUrl("/signin?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login");
        http.logout(logout ->
                logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
        );
        //csrf
        http.csrf().disable();
        //提交name="remember"
        http.rememberMe().rememberMeParameter("remember");
        //单一session
        http.sessionManagement().maximumSessions(1).expiredUrl("/signin");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder)
                .withUser("juminiy@2084team.cn").password(bCryptPasswordEncoder.encode("2084team")).roles("admin","student","staff")
                .and()
                .withUser("nomanker@2084team.cn").password(bCryptPasswordEncoder.encode("2084team")).roles("admin","student","staff")
                .and()
                .withUser("ggssh@2084team.cn").password(bCryptPasswordEncoder.encode("2084team")).roles("admin","student","staff");

        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

}
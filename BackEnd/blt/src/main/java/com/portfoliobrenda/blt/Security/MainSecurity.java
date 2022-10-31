package com.portfoliobrenda.blt.Security;

import com.portfoliobrenda.blt.Security.Service.UserDetailsImpl;
import com.portfoliobrenda.blt.Security.jwt.JwtEntryPoint;
import com.portfoliobrenda.blt.Security.jwt.JwtTokenFilter;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("deprecation")
public class MainSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsImpl userDetailsImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;
   
    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsImpl).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
            return super.authenticationManager();
    }

    
        @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> list1 = Arrays.asList(new String[]{"Authorization", "Cache-Control", "Content-Type"});
        List<String> list2 = Arrays.asList(new String[]{"https://frontendblt.web.app"});
        List<String> list3 = Arrays.asList(new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"});
        List<String> list4 = Arrays.asList(new String[]{"Authorization"});

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(list1);
        corsConfiguration.setAllowedOrigins(list2);
        corsConfiguration.setAllowedMethods(list3);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(list4);

        http.csrf().disable();
        http.authorizeRequests().antMatchers("**").permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().configurationSource(request -> corsConfiguration);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
package com.diti5.hopital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService ;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    /*
    @Value("${spring.queries.utilisateur-query}")
    private String usersQuery;

    @Value("${spring.queries.role-query}")
    private String rolesQuery;
*/
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
        userDetailsService(userDetailsService)
                //jdbcAuthentication()
               // .usersByUsernameQuery(usersQuery)
                //.authoritiesByUsernameQuery(rolesQuery)
                //.dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("______"+bCryptPasswordEncoder.encode("admin"));
        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/javax.faces.resource/**")
                .permitAll();

        // login
        http.formLogin().loginPage("/login.xhtml").permitAll()
                .failureUrl("/login.xhtml?error=true")
        .successHandler(myAuthenticationSuccessHandler())
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);;
         //role
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")//access("hasRole('ADMIN') or hasRole('ADMIN')")//hasRole("ADMIN")
                .antMatchers("/medecin/**").hasAnyAuthority("MEDECIN")
                .antMatchers("/secretaire/**").hasAnyAuthority("SECRETAIRE")
                .anyRequest()
                .authenticated();
        // logout
        http.logout().logoutSuccessUrl("/login.xhtml");
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web
                .ignoring()
                .antMatchers( "/assets/**","/css/**", "/js/**","/images/**","/upload/**");
    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {

    	return new MySimpleUrlAuthenticationSuccessHandler();
    }

}
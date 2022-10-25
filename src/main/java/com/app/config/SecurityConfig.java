package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	return http.httpBasic().and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.build();
}

@Bean
public UserDetailsService userDetailsService() {
	var uds= new InMemoryUserDetailsManager();

   var u1=User.withUsername("bill")
		   .authorities("read")
		   .password(passwordEncoder().encode("1234"))
		   .build();
   uds.createUser(u1);
   var u2= User.withUsername("arjun")
		      .authorities("write")
              .password(passwordEncoder().encode("1234"))
              .build();
   uds.createUser(u2);  
   return uds;
}

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

}




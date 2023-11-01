package com.MediLabo.MSFrontend.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(withDefaults())
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/login").permitAll()
						.requestMatchers("/images/**").permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/home")
						.permitAll())
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout")).logoutSuccessUrl("/login")
						.permitAll())
				.exceptionHandling(handling -> handling.accessDeniedPage("/access-denied"));
		return http.build();
	}

	@Bean
	UserDetailsService users() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

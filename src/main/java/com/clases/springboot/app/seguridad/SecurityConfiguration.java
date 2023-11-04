package com.clases.springboot.app.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.clases.springboot.app.Models.service.IUsuarioService;



@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
	private IUsuarioService clienteService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(clienteService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers("/login", "/signup", "/css/**", "/imagenes/**", "/js/**", "/nosotros","/createuser","/contacto","/img/**")
				.permitAll()
				.requestMatchers("/listuser","/listaproductos","/listaventas","/listapagos","/listadelivery").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/home", true)
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();
		return http.build();

	}

}

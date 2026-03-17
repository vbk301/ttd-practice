package com.springboot.api.gateaway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class GatewayConfig {
	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

		http.csrf(csrf -> csrf.disable()).authorizeExchange(auth -> auth
				.pathMatchers("/", "/index.html", "/assets/**", "/*.js","/*.css","/*.ico").permitAll().anyExchange().authenticated())
				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MapReactiveUserDetailsService  userDetailsService(PasswordEncoder encoder) {

		UserDetails user = User.builder().username("ttd.#.@.$").password(encoder.encode("Darshan@@##@@@")).roles("USER").build();

		return new MapReactiveUserDetailsService (user);
	}

//	@Controller
//	public class AngularForwardController {
//		@RequestMapping(value = { "/{path:[^\\.]*}", "/**/{path:[^\\.]*}" })
//		public String redirect() {
//			return "forward:/index.html";
//
//		}

//	}

}

//package esdi.Services.security;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//@AllArgsConstructor
//public class WebSecurityConfig{
//
//    private final UserDetailsService userDetailsService;
//    private final JWTAuthorizationFilter jwtAuthorizationFilter;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//
//            JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
//            jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
//            jwtAuthenticationFilter.setFilterProcessesUrl("/login");
//
//            return http
//                    .csrf().disable()
//                    .authorizeRequests()
////                        .antMatchers(HttpMethod.POST,"/login").permitAll()
////                        .antMatchers("/swagger-ui/**", "/v3/**").permitAll()
////                        .antMatchers(HttpMethod.GET,"/companies/**", "/SAC/orders").hasRole("ADMIN")
////                        .antMatchers(HttpMethod.GET,"/SAC/categories").hasRole("USER")
////                        .antMatchers("/admin/**","/rest/**","/h2-console/**", "/companies/**", "/orders/**","/staff/**","/services/**","/SAC/**").hasAuthority("SUPER_ADMIN")
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .addFilter(jwtAuthenticationFilter)
//                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                    .build();
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity http,PasswordEncoder passwordEncoder) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    };
//
//}


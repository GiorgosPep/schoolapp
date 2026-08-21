package gr.aueb.cf.schoolapp10.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity      // Filter Security
@EnableMethodSecurity   // Enable το @PreAuthorize
@RequiredArgsConstructor // DI
public class SecurityConfig {

    // auth success handler
    // auth failure handler

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("users/register", "/users/success").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/users-success").permitAll()
                        .requestMatchers("/teachers/insert").hasAuthority("INSERT_TEACHER")
                        .requestMatchers("/teachers/delete").hasAuthority("DELETE_TEACHER")
                        .requestMatchers(HttpMethod.GET,"/teachers/edit/{uuid}").hasAuthority("EDIT_TEACHER")
                        .requestMatchers(HttpMethod.POST,"/teachers/edit/{uuid}").hasAuthority("EDIT_TEACHER")
                        .requestMatchers(HttpMethod.GET,"/teachers/update-success").hasAuthority("EDIT_TEACHER")
                        .requestMatchers(HttpMethod.POST, "/teachers/delete/{uuid}").hasAuthority("DELETE_TEACHER")
                        .requestMatchers(HttpMethod.GET, "/teachers/delete-success").hasAuthority("DELETE_TEACHER")
                        .requestMatchers("/teachers/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/css/**",
                                "/js/**",
                                "/img/**",
                                "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
//                        .successHandler(auth successHandler())
//                        .failureHandler(failure handler)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")  // δουλεύει με post
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}

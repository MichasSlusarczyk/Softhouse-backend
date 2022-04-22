package pl.polsl.softhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.polsl.softhouse.components.security.JwtFilter;
import pl.polsl.softhouse.components.security.UserEntityDetailsService;
import pl.polsl.softhouse.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final JwtFilter jwtFilter;
    private final UserEntityDetailsService uds;

    public SecurityConfig(UserRepository userRepository, JwtFilter jwtFilter, UserEntityDetailsService uds) {
        this.userRepository = userRepository;
        this.jwtFilter = jwtFilter;
        this.uds = uds;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF.
        http = http.csrf().disable().cors().and();

        // Set session to stateless.
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests handler.
        http = http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, ex) -> {
                    response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage());
                })
                .and();

        // Endpoints permissions
        setEndpointsPermissions(http);

        // Add JWT token filter.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void setEndpointsPermissions(HttpSecurity http) {

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

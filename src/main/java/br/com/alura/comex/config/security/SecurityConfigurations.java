package br.com.alura.comex.config.security;

import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigurations {

    @Autowired
    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationService autenticacaoService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration, ApplicationContext context, ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        authenticationConfiguration.authenticationManagerBuilder(objectPostProcessor, context)
                .userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizedRequests -> authorizedRequests
                                .antMatchers(HttpMethod.GET, "/categorias/pedidos").permitAll()
                                .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                                .antMatchers("/swagger-ui/*").permitAll()
                                .antMatchers("/v3/api-docs/**").permitAll()
                                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                                .anyRequest().authenticated())
                                .csrf().disable()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

package com.Coupon.Security.config;

import com.Coupon.Security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor

public class ApplicationConfiguration {
    public final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService(){//we change the userDetailsService so it can use th userRepository to get the User from our dataBase and we set it in the authProvider with the passwordEncoder
        return  username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

    }
    //this to instead of creating a new service that implements the userdeatailsService and override the loadByUsername method


    @Bean
    public AuthenticationProvider authenticationProvider(){//will be used by the authenticationManager
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }//fetch userdetail /encode passwords
    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.out.println("manager");
        return config.getAuthenticationManager();
    }//we are using default importaion
}

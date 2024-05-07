package com.Coupon.Security.auth;

import com.Coupon.Security.Utilities.JwtService;
import com.Coupon.Security.user.Role;
import com.Coupon.Security.user.User;
import com.Coupon.Security.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if(repository.existsById(request.getEmail()))
            return null;
   var user = User.builder()
           .firstname(request.getFirstname())
           .lastname(request.getLastname())
           .email(request.getEmail())
           .password(passwordEncoder.encode(request.getPassword()))
           .role(Role.USER)
           .build();
   repository.save(user);
   var jwtToken=jwtService.generateToken(user);
      return  AuthenticationResponse.builder().token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail()).
              role(user.getRole()).
                build();

    }
    public  AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {


try{
    //will verify if there is a user in the database with these credentials
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())) ;

}    catch (DisabledException e)
{
    e.printStackTrace();

}
catch (BadCredentialsException e)
{
    System.out.println("Bad Credentials ");
    e.printStackTrace();
return null;
}


//if the authentication manager went with no problems it's time to generate The token
        var user= repository.findByEmail(request.getEmail()) .orElseThrow(() -> new Exception("User not found with email: " + request.getEmail()));
        var jwtToken=jwtService.generateToken(user);

        return  AuthenticationResponse.builder().token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail()).
                role(user.getRole()).
                build();   }



    @PostConstruct
    void createAdmin(){
        if(!repository.existsById("admin@gmail.com")) {
           User user= User.builder().email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .firstname("admin")
                    .lastname("admin")
                    .password(passwordEncoder.encode( "admin"))
                    .build();
            repository.save(user);
        }
    }
}

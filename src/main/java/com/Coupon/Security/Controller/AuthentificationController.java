package com.Coupon.Security.Controller;

import com.Coupon.Security.auth.AuthenticationRequest;
import com.Coupon.Security.auth.AuthenticationResponse;
import com.Coupon.Security.auth.AuthenticationService;
import com.Coupon.Security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {
    private final AuthenticationService authenticationService;
@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
return  ResponseEntity.ok(authenticationService.register(request));
}
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request ) throws Exception {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/hello")
    String hello(){
        return "ta3ala achab3ak 7ob achab3ak dalal";
    }


}

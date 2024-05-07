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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
    AuthenticationResponse response=authenticationService.register(request);
    if(response==null)
        return  ResponseEntity.badRequest().body("user already exists");

    return  ResponseEntity.ok(response);
}
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request ) throws Exception {

    AuthenticationResponse response=authenticationService.authenticate(request);
    if(response == null)
        return  ResponseEntity.badRequest().body("user unauthorized");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/hello")
    String hello(){
        return "ta3ala achab3ak 7ob achab3ak dalal";
    }


}

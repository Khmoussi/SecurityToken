package com.Coupon.Security.auth;

import com.Coupon.Security.user.Role;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String email;

    private String firstname;
    private String lastname;

    private String token;
    private Role role;
}

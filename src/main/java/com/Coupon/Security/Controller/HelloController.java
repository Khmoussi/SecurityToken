package com.Coupon.Security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
        String hello(){
        return "ta3ala achab3ak 7ob achab3ak dalal";
        }
}

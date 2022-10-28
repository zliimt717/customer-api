package com.example.customerapidemo.controller;

import com.example.customerapidemo.entity.UserCustomer;
import com.example.customerapidemo.repository.UserCustomerRepository;
import com.example.customerapidemo.request.AuthRequest;
import com.example.customerapidemo.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserCustomerRepository userCustomerRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = "/register")
    public ResponseEntity<UserCustomer> register(@RequestBody AuthRequest authRequest){
        Optional<UserCustomer> userCustomerOptional=
                userCustomerRepository.findByEmail(authRequest.getEmail());

        if(userCustomerOptional.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        UserCustomer userCustomer=new UserCustomer();
        userCustomer.setEmail(authRequest.getEmail());
        userCustomer.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        UserCustomer created=userCustomerRepository.save(userCustomer);

        return ResponseEntity.ok(created);
    }

}

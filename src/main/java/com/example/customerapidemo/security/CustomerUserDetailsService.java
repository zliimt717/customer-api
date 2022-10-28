package com.example.customerapidemo.security;

import com.example.customerapidemo.entity.UserCustomer;
import com.example.customerapidemo.repository.UserCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserCustomerRepository userCustomerRepository;

    public CustomerUserDetailsService(UserCustomerRepository userCustomerRepository) {
        this.userCustomerRepository = userCustomerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCustomer userCustomer=userCustomerRepository.findByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("User is not found")
                );
        return new org.springframework.security.core.userdetails.User(
                userCustomer.getEmail(),
                userCustomer.getPassword(),
                Collections.emptyList()
        );
    }

}

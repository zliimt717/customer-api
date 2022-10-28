package com.example.customerapidemo;

import com.example.customerapidemo.entity.UserCustomer;
import com.example.customerapidemo.repository.UserCustomerRepository;
import com.example.customerapidemo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomerApiApplication {

	/*@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserCustomerRepository userCustomerRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;*/

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}


	/*@Override
	public void run(String... args) throws Exception {
		try {
			UserCustomer userCustomer=new UserCustomer();
			userCustomer.setEmail("test@test.de");
			userCustomer.setPassword(passwordEncoder.encode("1234"));

			UserCustomer saved=userCustomerRepository.save(userCustomer);
		}catch (Exception e){
			new IllegalAccessException(e.getMessage());
		}
	}*/

}

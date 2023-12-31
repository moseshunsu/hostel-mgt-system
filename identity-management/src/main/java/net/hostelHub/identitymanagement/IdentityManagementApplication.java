package net.hostelHub.identitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class IdentityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityManagementApplication.class, args);
	}

}

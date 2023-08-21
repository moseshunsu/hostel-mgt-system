package net.hostelHub.tenantmgtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TenantMgtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantMgtServiceApplication.class, args);
	}

}

package net.hostelHub.gatewayserver;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
        HttpClient client =
                HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//        HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);

		SpringApplication.run(GatewayServerApplication.class, args);
	}

}

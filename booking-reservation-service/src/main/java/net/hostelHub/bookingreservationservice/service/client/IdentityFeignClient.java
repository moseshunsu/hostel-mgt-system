package net.hostelHub.bookingreservationservice.service.client;

import net.hostelHub.bookingreservationservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "IDENTITY-MANAGEMENT")
public interface IdentityFeignClient {

    @GetMapping("api/v1/users/{emailOrUsername}")
    User fetchUser(@PathVariable String emailOrUsername);

}

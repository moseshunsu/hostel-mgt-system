package net.HostelHub.roommgtservice.service.client;

import net.HostelHub.roommgtservice.dto.HostelProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "TENANT-SERVICE")
public interface TenantFeignClient {

    @GetMapping("/properties")
    HostelProperty fetchProperty(@RequestParam String name, @RequestParam String school);

}

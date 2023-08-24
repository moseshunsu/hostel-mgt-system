package net.HostelHub.roommgtservice.service.client;

import net.HostelHub.roommgtservice.dto.HostelPropertyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "TENANT-SERVICE")
public interface TenantFeignClient {

    @GetMapping("api/v1/tenants/properties")
    HostelPropertyDto fetchProperty(@RequestParam("hostelName") String hostelName,
                                    @RequestParam("school") String school);

}

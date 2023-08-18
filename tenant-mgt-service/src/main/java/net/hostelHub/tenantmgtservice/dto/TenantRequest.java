package net.hostelHub.tenantmgtservice.dto;


import lombok.*;
import net.hostelHub.tenantmgtservice.entity.HostelProperty;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class TenantRequest {

    private String phoneNumber;
    private String address;
    private String city;

}

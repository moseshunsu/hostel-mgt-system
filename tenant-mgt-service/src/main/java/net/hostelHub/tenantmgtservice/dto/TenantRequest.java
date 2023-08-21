package net.hostelHub.tenantmgtservice.dto;


import lombok.*;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class TenantRequest {

    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private String city;

}

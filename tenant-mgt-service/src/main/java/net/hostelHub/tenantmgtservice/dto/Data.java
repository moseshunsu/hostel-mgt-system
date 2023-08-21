package net.hostelHub.tenantmgtservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class Data {

    private String clientCode;
    private String email;
    private String username;

}

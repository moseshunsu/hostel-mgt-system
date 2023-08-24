package net.HostelHub.roommgtservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class Data {

    private String tenantCode;
    private String hostelName;
    private String roomNumber;

}

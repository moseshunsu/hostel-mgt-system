package net.HostelHub.roommgtservice.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class HostelPropertyDto {
    private String hostelName;
    private String school;
    private String type;
    private String tenantCode;
}

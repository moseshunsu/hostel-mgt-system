package net.hostelHub.tenantmgtservice.dto;

import lombok.*;
import net.hostelHub.tenantmgtservice.utils.School;
import net.hostelHub.tenantmgtservice.utils.State;
import net.hostelHub.tenantmgtservice.utils.Type;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class HostelPropertyRequest {

    private String hostelName;
    private School school;
    private State state;
    private String address;
    private String description;
    private int totalRooms;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String tenantCode;
    private Type type;

}

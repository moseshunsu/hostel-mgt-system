package net.hostelHub.tenantmgtservice.dto;

import lombok.*;
import net.hostelHub.tenantmgtservice.entity.School;
import net.hostelHub.tenantmgtservice.entity.State;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class HostelPropertyRequest {

    private String name;
    private School school;
    private State state;
    private String address;
    private String description;
    private int totalRooms;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}

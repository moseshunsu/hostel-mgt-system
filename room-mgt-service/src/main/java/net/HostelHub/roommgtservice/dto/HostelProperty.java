package net.HostelHub.roommgtservice.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class HostelProperty {
    private Long id;
    private String name;
    private String address;
    private int totalRooms;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}

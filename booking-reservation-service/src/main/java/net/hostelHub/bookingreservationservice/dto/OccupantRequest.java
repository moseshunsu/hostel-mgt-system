package net.hostelHub.bookingreservationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OccupantRequest {
    private String occupantCode;
    private String email;
    private String name;
    private String username;
    private String phoneNumber;
}

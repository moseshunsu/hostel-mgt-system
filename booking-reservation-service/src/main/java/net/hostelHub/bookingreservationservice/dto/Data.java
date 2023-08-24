package net.hostelHub.bookingreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class Data {

    private String occupantCode;
    private String email;
    private String roomNumber;

}

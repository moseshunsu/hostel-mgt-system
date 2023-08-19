package net.hostelHub.identitymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data @AllArgsConstructor
@Builder @NoArgsConstructor
public class Data {

    private String email;
    private String clientCode;

}

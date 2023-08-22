package net.hostelHub.tenantmgtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.hostelHub.tenantmgtservice.utils.School;

@Setter @Getter @Builder
public class PropertyPhotoRequest {
    private String propertyName;
    private School school;
    private String photoUrl;
    private String description;
//    private String propertyId;
}

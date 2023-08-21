package net.hostelHub.tenantmgtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.hostelHub.tenantmgtservice.entity.School;

@Setter @Getter @Builder
public class PriceListRequest {
    private String propertyName;
    private School school;
    private Integer numberOfPersons;
    private Double price;
//    private String propertyId;
}

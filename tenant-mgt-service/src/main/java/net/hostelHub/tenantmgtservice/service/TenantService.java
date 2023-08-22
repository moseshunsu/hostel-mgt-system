package net.hostelHub.tenantmgtservice.service;

import net.hostelHub.tenantmgtservice.dto.*;
import org.springframework.http.ResponseEntity;

public interface TenantService {
    ResponseEntity<Response> updateDetails(TenantRequest tenantRequest);
    ResponseEntity<Response> registerProperty(HostelPropertyRequest hostelPropertyRequest);
    HostelPropertyDto fetchProperty(String hostelName,String school);
    ResponseEntity<Response> addPhoto(PropertyPhotoRequest propertyPhotoRequest);
}

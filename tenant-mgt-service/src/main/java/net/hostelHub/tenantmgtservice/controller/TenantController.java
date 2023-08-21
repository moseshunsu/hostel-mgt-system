package net.hostelHub.tenantmgtservice.controller;

import net.hostelHub.tenantmgtservice.dto.*;
import net.hostelHub.tenantmgtservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {

    @Autowired
    TenantService tenantService;

    @PostMapping
    public ResponseEntity<Response> registerDetails(@RequestBody TenantRequest tenantRequest) {
        return tenantService.updateDetails(tenantRequest);
    }

    @PostMapping("/properties")
    public ResponseEntity<Response> registerProperty(@RequestBody HostelPropertyRequest hostelPropertyRequest) {
        return tenantService.registerProperty(hostelPropertyRequest);
    }

    @PostMapping("/properties/prices")
    public ResponseEntity<Response> addPrice(PriceListRequest priceListRequest) {
        return tenantService.addPrice(priceListRequest);
    }

    @PostMapping("/properties/photos")
    public ResponseEntity<Response> addPhoto(PropertyPhotoRequest propertyPhotoRequest) {
        return tenantService.addPhoto(propertyPhotoRequest);
    }
}

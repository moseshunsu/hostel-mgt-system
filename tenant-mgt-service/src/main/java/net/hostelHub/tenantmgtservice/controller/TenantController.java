package net.hostelHub.tenantmgtservice.controller;

import net.hostelHub.tenantmgtservice.dto.*;
import net.hostelHub.tenantmgtservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/properties")
    public HostelPropertyDto fetchProperty(@RequestParam String hostelName, @RequestParam String school) {
        return tenantService.fetchProperty(hostelName, school);
    }

    @PostMapping("/properties/photos")
    public ResponseEntity<Response> addPhoto(PropertyPhotoRequest propertyPhotoRequest) {
        return tenantService.addPhoto(propertyPhotoRequest);
    }
}

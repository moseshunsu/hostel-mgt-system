package net.hostelHub.tenantmgtservice.service;

import net.hostelHub.tenantmgtservice.dto.HostelPropertyRequest;
import net.hostelHub.tenantmgtservice.dto.Response;
import net.hostelHub.tenantmgtservice.dto.TenantRequest;
import org.springframework.http.ResponseEntity;

public interface TenantService {
    ResponseEntity<Response> updateDetails(TenantRequest tenantRequest);
    ResponseEntity<Response> registerProperty(HostelPropertyRequest hostelPropertyRequest);
}

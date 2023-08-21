package net.hostelHub.tenantmgtservice.service;

import net.hostelHub.tenantmgtservice.dto.Data;
import net.hostelHub.tenantmgtservice.dto.Response;
import net.hostelHub.tenantmgtservice.dto.TenantRequest;
import net.hostelHub.tenantmgtservice.dto.User;
import net.hostelHub.tenantmgtservice.entity.Tenant;
import net.hostelHub.tenantmgtservice.repository.TenantRepository;
import net.hostelHub.tenantmgtservice.service.client.IdentityFeignClient;
import net.hostelHub.tenantmgtservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    IdentityFeignClient identityFeignClient;

    @Override
    public ResponseEntity<Response> updateDetails(TenantRequest tenantRequest) {

        boolean isTenantExists = tenantRepository.existsByEmailOrUsername(tenantRequest.getEmail(),
                tenantRequest.getUsername());

        if (isTenantExists) {
            Tenant fetchedTenant = tenantRepository.findByEmailOrUsername(tenantRequest.getEmail(),
                    tenantRequest.getUsername());

            if (tenantRequest.getCity() != null) {
                fetchedTenant.setCity(tenantRequest.getCity());
            }
            if (tenantRequest.getAddress() != null) {
                fetchedTenant.setAddress(tenantRequest.getAddress());
            }
            if (tenantRequest.getPhoneNumber() != null) {
                fetchedTenant.setPhoneNumber(tenantRequest.getPhoneNumber());
            }
            tenantRepository.save(fetchedTenant);

            return ResponseEntity.ok().body(
                    Response.builder()
                            .responseCode(ResponseUtils.SUCCESS_CODE)
                            .responseMessage(ResponseUtils.DETAILS_ENTRY_SUCCESS)
                            .data(
                                    Data.builder()
                                            .clientCode(fetchedTenant.getTenantCode())
                                            .email(fetchedTenant.getEmail())
                                            .username(fetchedTenant.getUsername())
                                            .build()
                            )
                            .build()
            );
        }

        User fetchedUser = identityFeignClient.fetchUser(tenantRequest.getEmail());

        Tenant tenant = new Tenant();
        tenant.setEmail(fetchedUser.getEmail());
        tenant.setName(fetchedUser.getName());
        tenant.setUsername(fetchedUser.getUsername());
        tenant.setPhoneNumber(tenantRequest.getPhoneNumber());
        tenant.setAddress(tenantRequest.getAddress());
        tenant.setCity(tenantRequest.getCity());
        tenant.setTenantCode(ResponseUtils.generateClientCode(ResponseUtils.LENGTH_OF_CLIENT_CODE));

        Tenant savedTenant = tenantRepository.save(tenant);

        return ResponseEntity.ok().body(
                Response.builder()
                        .responseCode(ResponseUtils.SUCCESS_CODE)
                        .responseMessage(ResponseUtils.DETAILS_ENTRY_SUCCESS)
                        .data(
                                Data.builder()
                                        .clientCode(savedTenant.getTenantCode())
                                        .email(savedTenant.getEmail())
                                        .username(savedTenant.getUsername())
                                        .build()
                        )
                        .build()
        );
    }
}

package net.hostelHub.tenantmgtservice.service;

import net.hostelHub.tenantmgtservice.dto.*;
import net.hostelHub.tenantmgtservice.entity.HostelProperty;
import net.hostelHub.tenantmgtservice.entity.Tenant;
import net.hostelHub.tenantmgtservice.repository.HostelPropertyRepository;
import net.hostelHub.tenantmgtservice.repository.TenantRepository;
import net.hostelHub.tenantmgtservice.service.client.IdentityFeignClient;
import net.hostelHub.tenantmgtservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    IdentityFeignClient identityFeignClient;
    @Autowired
    private HostelPropertyRepository hostelPropertyRepository;

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

    @Override
    public ResponseEntity<Response> registerProperty(HostelPropertyRequest hostelPropertyRequest) {

        List<HostelProperty> fetchedProperties = hostelPropertyRepository.findByName(hostelPropertyRequest.getName());
        boolean propertyExists = fetchedProperties.stream()
                .anyMatch(hostel -> hostel.getName().equalsIgnoreCase(hostelPropertyRequest.getName()) &&
                                    hostel.getSchool().equals(hostelPropertyRequest.getSchool())
                );

        if (propertyExists) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseCode(ResponseUtils.PROPERTY_EXISTS_CODE)
                    .responseMessage(ResponseUtils.PROPERTY_EXISTS_MESSAGE)
                    .data(
                            Data.builder()
                                    .username(hostelPropertyRequest.getName())
                                    .build()
                    )
                    .build()
            );
        }


        HostelProperty property = new HostelProperty();
        property.setName(hostelPropertyRequest.getName());
        property.setSchool(hostelPropertyRequest.getSchool());
        property.setState(hostelPropertyRequest.getState());
        property.setAddress(hostelPropertyRequest.getAddress());
        property.setDescription(hostelPropertyRequest.getDescription());
        property.setTotalRooms(hostelPropertyRequest.getTotalRooms());
        property.setContactName(hostelPropertyRequest.getContactName());
        property.setContactEmail(hostelPropertyRequest.getContactEmail());
        property.setContactPhone(hostelPropertyRequest.getContactPhone());
        property.setType(hostelPropertyRequest.getType());
        property.setTenant(tenantRepository.findByTenantCode(hostelPropertyRequest.getTenantCode()));

        HostelProperty savedProperty = hostelPropertyRepository.save(property);

        return ResponseEntity.ok().body(
                Response.builder()
                        .responseCode(ResponseUtils.SUCCESS_CODE)
                        .responseMessage(ResponseUtils.REGISTER_PROPERTY_SUCCESS)
                        .data(
                                Data.builder()
                                        .clientCode(savedProperty.getTenant().getTenantCode())
                                        .username(savedProperty.getTenant().getUsername())
                                        .email(savedProperty.getTenant().getEmail())
                                        .build()
                        )
                        .build()
        );
    }
}

package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, String> {
    boolean existsByEmailOrUsername(String email, String username);
    Tenant findByEmailOrUsername(String email, String username);
    Tenant findByTenantCode(String tenantCode);
}

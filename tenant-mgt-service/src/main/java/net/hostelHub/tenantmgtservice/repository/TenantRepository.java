package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, String> {
}

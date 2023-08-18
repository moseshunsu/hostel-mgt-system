package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.HostelProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelPropertyRepository extends JpaRepository<HostelProperty, Long> {
}

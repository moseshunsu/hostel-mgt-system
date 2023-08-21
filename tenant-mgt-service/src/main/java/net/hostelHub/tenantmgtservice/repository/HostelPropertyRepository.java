package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.HostelProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostelPropertyRepository extends JpaRepository<HostelProperty, Long> {
    List<HostelProperty> findByName(String name);
    boolean existsByName(String hostelName);
}

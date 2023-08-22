package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.HostelProperty;
import net.hostelHub.tenantmgtservice.utils.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostelPropertyRepository extends JpaRepository<HostelProperty, Long> {
    HostelProperty findByHostelNameAndSchool(String name, School school);
    boolean existsByHostelNameAndSchool(String name, School school);
    List<HostelProperty> findByHostelName(String name);
    boolean existsByHostelName(String hostelName);
}

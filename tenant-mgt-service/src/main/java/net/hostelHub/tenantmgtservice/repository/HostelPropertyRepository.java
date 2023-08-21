package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.HostelProperty;
import net.hostelHub.tenantmgtservice.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostelPropertyRepository extends JpaRepository<HostelProperty, Long> {
//    HostelProperty findByNameAndSchool(String name, School school);
    HostelProperty findByNameAndSchool(String name, School school);
    List<HostelProperty> findByName(String name);
    boolean existsByName(String hostelName);
}

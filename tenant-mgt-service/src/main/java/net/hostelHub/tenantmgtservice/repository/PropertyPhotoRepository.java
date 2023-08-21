package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {
}

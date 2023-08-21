package net.hostelHub.tenantmgtservice.repository;

import net.hostelHub.tenantmgtservice.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
}

package com.pasto.emergency.repository;

import com.pasto.emergency.model.entity.Emergency;
import com.pasto.emergency.model.enums.EmergencyStatus;
import com.pasto.emergency.model.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    List<Emergency> findByStatus(EmergencyStatus status);
    List<Emergency> findByAssignedService(ServiceType service);
    Optional<Emergency> findByReportId(Long reportId);
}
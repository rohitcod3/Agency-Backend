package net.agencyM.agency_backend.repository;

import net.agencyM.agency_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}

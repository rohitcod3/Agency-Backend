package net.agencyM.agency_backend.repository;

import net.agencyM.agency_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

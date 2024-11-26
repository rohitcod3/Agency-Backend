package net.agencyM.agency_backend.repository;

import net.agencyM.agency_backend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByClientEmail(String clientEmail);
}

package io.paperless.central.repositories;

import io.paperless.central.models.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
}



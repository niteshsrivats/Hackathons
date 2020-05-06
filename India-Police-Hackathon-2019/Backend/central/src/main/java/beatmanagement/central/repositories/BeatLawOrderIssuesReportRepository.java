package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatLawOrderIssuesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatLawOrderIssuesReportRepository extends JpaRepository<BeatLawOrderIssuesReport, Long> {

    Optional<BeatLawOrderIssuesReport> findById(Long id);
}

package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatIllegalActivitiesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatIllegalActivitiesReportRepository extends JpaRepository<BeatIllegalActivitiesReport, Long> {

    Optional<BeatIllegalActivitiesReport> findById(Long id);
}

package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatExpectationsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatExpectationReportRepository extends JpaRepository<BeatExpectationsReport, Long> {

    Optional<BeatExpectationsReport> findById(Long id);

//    List<BeatExpectationsReport> findBeatExpectationsReport
}

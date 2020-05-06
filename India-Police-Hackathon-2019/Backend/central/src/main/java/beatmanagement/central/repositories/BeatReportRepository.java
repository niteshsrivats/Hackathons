package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatReportRepository extends JpaRepository<BeatReport, Long> {

    Optional<BeatReport> findById(Long id);

    List<BeatReport> findBeatReportsByStartTimeIsBetween(Long startTime, Long endTime);
}

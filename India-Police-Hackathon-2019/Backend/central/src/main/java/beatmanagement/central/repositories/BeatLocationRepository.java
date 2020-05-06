package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatLocationRepository extends JpaRepository<BeatLocation, Long> {

    List<BeatLocation> findBeatLocationsByBeatReportId(Long beatReportId);

    List<BeatLocation> findBeatLocationsByBeatReportIdOrderByIdDesc(Long beatReportId);
}

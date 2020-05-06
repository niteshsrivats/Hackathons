package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.Beat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatRepository extends JpaRepository<Beat, Long> {

    Optional<Beat> findById(Long id);

    List<Beat> findBeatsByStation_IdOrderByIdAsc(Long stationId);

}

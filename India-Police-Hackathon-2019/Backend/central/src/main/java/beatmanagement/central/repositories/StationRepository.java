package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findById(String id);
}

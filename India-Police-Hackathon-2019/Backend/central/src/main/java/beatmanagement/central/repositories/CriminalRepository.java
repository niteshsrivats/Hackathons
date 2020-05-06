package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {

    Optional<Criminal> findById(Long id);

    List<Criminal> findCriminalsByBeatId(Long beatId);
}

package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.Crime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface CrimeRepository extends JpaRepository<Crime, Long> {

    Optional<Crime> findById(Long id);

    List<Crime> findCrimesByBeatId(Long beatId);
}

package beatmanagement.central.repositories;

import beatmanagement.central.models.constants.OfficerType;
import beatmanagement.central.models.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {

    Optional<Officer> findById(Long id);

    List<Officer> findOfficersByStation_IdAndOfficerType(Long stationId, OfficerType officerType);
}

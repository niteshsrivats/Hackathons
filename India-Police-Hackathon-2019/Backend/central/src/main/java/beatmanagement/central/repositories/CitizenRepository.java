package beatmanagement.central.repositories;

import beatmanagement.central.models.constants.CitizenType;
import beatmanagement.central.models.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findById(Long id);

    List<Citizen> findCitizensByBeat_IdAndCitizenType(Long beatId, CitizenType citizenType);
}

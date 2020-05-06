package beatmanagement.central.repositories;

import beatmanagement.central.models.entities.BeatBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface BeatBookRepository extends JpaRepository<BeatBook, Long> {

    Optional<BeatBook> findById(Long id);

    Optional<BeatBook> findBeatBookByBeat_Id(Long beatId);
}

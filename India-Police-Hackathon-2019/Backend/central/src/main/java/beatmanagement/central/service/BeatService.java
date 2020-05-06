package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.Beat;
import beatmanagement.central.models.entities.BeatBook;
import beatmanagement.central.repositories.BeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatService {

    private final BeatRepository beatRepository;
    private final BeatBookService beatBookService;

    @Autowired
    public BeatService(BeatRepository beatRepository, BeatBookService beatBookService) {
        this.beatRepository = beatRepository;
        this.beatBookService = beatBookService;
    }

    @Transactional
    public Beat addBeat(Beat beat) {
        beat = beatRepository.save(beat);
        BeatBook beatBook = new BeatBook(beat, null);
        beatBookService.addBeatBook(beatBook);
        return beat;
    }

    @Transactional
    public Beat getBeatById(Long id) {
        return beatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Beat.class, id));
    }

    @Transactional
    public List<Beat> getBeatsByStationId(Long stationId) {
        List<Beat> beats = beatRepository.findBeatsByStation_IdOrderByIdAsc(stationId);
        if (beats.isEmpty()) {
            throw new EntityNotFoundException(Beat.class, stationId);
        }
        return beats;
    }
}

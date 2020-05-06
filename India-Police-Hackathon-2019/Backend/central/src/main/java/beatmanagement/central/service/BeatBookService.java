package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.BeatBook;
import beatmanagement.central.repositories.BeatBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatBookService {

    private final BeatBookRepository beatBookRepository;

    @Autowired
    public BeatBookService(BeatBookRepository beatBookRepository) {
        this.beatBookRepository = beatBookRepository;
    }

    @Transactional
    public BeatBook addBeatBook(BeatBook beatBook) {
        return beatBookRepository.save(beatBook);
    }

    @Transactional
    public BeatBook getBeatBookById(Long id) {
        return beatBookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BeatBook.class, id));
    }

    @Transactional
    public BeatBook getBeatBookByBeatId(Long beatId) {
        return beatBookRepository.findBeatBookByBeat_Id(beatId).orElseThrow(() -> new EntityNotFoundException(BeatBook.class, beatId));
    }

    @Transactional
    public BeatBook updateBeatReport(BeatBook beatBook) {
        return beatBookRepository.save(beatBook);
    }
}

package beatmanagement.central.service;

import beatmanagement.central.models.entities.Beat;
import beatmanagement.central.models.entities.Criminal;
import beatmanagement.central.repositories.BeatRepository;
import beatmanagement.central.repositories.CriminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class CriminalService {

    private final CriminalRepository criminalRepository;
    private final BeatRepository beatRepository;

    @Autowired
    public CriminalService(CriminalRepository criminalRepository, BeatRepository beatRepository) {
        this.criminalRepository = criminalRepository;
        this.beatRepository = beatRepository;
    }

    public Criminal addCriminal(Criminal criminal) {
        return criminalRepository.save(criminal);
    }

    public List<Criminal> getCriminalsByBeatId(Long beatId) {
        return criminalRepository.findCriminalsByBeatId(beatId);
    }

    public Map<String, Integer> getCriminalData() {
        HashMap<String, Integer> beatCriminalSortedMap = new HashMap<>();
        for (Beat beat : beatRepository.findAll()) {
            beatCriminalSortedMap.put(beat.getId().toString(), getCriminalsByBeatId(beat.getId()).size());
        }
        return beatCriminalSortedMap;
    }
}

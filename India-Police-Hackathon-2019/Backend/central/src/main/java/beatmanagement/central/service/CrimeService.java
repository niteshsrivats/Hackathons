package beatmanagement.central.service;

import beatmanagement.central.models.entities.Beat;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.models.entities.Crime;
import beatmanagement.central.repositories.BeatRepository;
import beatmanagement.central.repositories.CrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class CrimeService {

    private final CrimeRepository crimeRepository;
    private final BeatReportService beatReportService;
    private final BeatRepository beatRepository;

    @Autowired
    public CrimeService(CrimeRepository crimeRepository, BeatReportService beatReportService, BeatRepository beatRepository) {
        this.crimeRepository = crimeRepository;
        this.beatReportService = beatReportService;
        this.beatRepository = beatRepository;
    }

    public List<Crime> getCrimeByBeatId(Long beatId) {
        return crimeRepository.findCrimesByBeatId(beatId);
    }

    public Integer getCrimeCountByBeatId(Long beatId) {
        return crimeRepository.findCrimesByBeatId(beatId).size();
    }

    public Crime addCrime(Long beatReportId, Crime crime) {
        crime = crimeRepository.save(crime);
        BeatReport beatReport = beatReportService.getBeatReportById(beatReportId);
        beatReport.getCrimes().add(crime);
        beatReportService.updateBeatReport(beatReport);
        return crime;
    }

    public Map<Long, Integer> getData() {
        HashMap<Long, Integer> crimeHashMap = new HashMap<>();
        for (Beat beat : beatRepository.findAll()) {
            List<Crime> crimes = getCrimeByBeatId(beat.getId());
            for (Crime crime : crimes) {
                crimeHashMap.put(crime.getTime() % 6, crimeHashMap.getOrDefault(crime.getTime() % 6, 0) + 1);
            }
        }
        return crimeHashMap;
    }
}

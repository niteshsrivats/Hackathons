package beatmanagement.central.service;

import beatmanagement.central.models.entities.BeatLocation;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.repositories.BeatLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatLocationService {

    private final BeatLocationRepository beatLocationRepository;
    private final BeatReportService beatReportService;

    @Autowired
    public BeatLocationService(BeatLocationRepository beatLocationRepository, BeatReportService beatReportService) {
        this.beatLocationRepository = beatLocationRepository;
        this.beatReportService = beatReportService;
    }

    @Transactional
    public List<BeatLocation> getBeatLocationsByBeatReportId(Long beatReportId) {
        return beatLocationRepository.findBeatLocationsByBeatReportId(beatReportId);
    }

    @Transactional
    public void addBeatLocations(Long beatReportId, BeatLocation beatLocation) {
//        BeatReport beatReport = beatReportService.getBeatReportById(beatReportId);
        beatLocation.setBeatReportId(beatReportId);
        beatLocationRepository.save(beatLocation);
    }

    @Transactional
    public Map<String, ArrayList<Double>> getLocation() {
        HashMap<String, ArrayList<Double>> stringListMap = new HashMap<String, ArrayList<Double>>();
        for (BeatReport beatReport: beatReportService.getAll()) {
            if (beatReport.getEndTime() == null) {
                List<BeatLocation> list = beatLocationRepository.findBeatLocationsByBeatReportIdOrderByIdDesc(beatReport.getId());
                if (!list.isEmpty()) {
                    ArrayList<Double> arrayList = new ArrayList<>();
                    arrayList.add(list.get(0).getLatitude());
                    arrayList.add(list.get(0).getLongitude());
                    stringListMap.put(beatReport.getOfficer().getId().toString(), arrayList);
                }
            }
        }
        return stringListMap;
    }
}

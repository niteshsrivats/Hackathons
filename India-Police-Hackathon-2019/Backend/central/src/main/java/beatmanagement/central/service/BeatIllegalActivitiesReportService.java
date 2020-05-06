package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.BeatExpectationsReport;
import beatmanagement.central.models.entities.BeatIllegalActivitiesReport;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.repositories.BeatIllegalActivitiesReportRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatIllegalActivitiesReportService {

    private final BeatIllegalActivitiesReportRepository beatIllegalActivitiesReportRepository;
    private final BeatReportService beatReportService;

    public BeatIllegalActivitiesReportService(BeatIllegalActivitiesReportRepository beatIllegalActivitiesReportRepository, BeatReportService beatReportService) {
        this.beatIllegalActivitiesReportRepository = beatIllegalActivitiesReportRepository;
        this.beatReportService = beatReportService;
    }

    @Transactional
    public BeatIllegalActivitiesReport addBeatIllegalActivitiesReport(Long beatReportId, BeatIllegalActivitiesReport beatIllegalActivitiesReport) {
        beatIllegalActivitiesReport = beatIllegalActivitiesReportRepository.save(beatIllegalActivitiesReport);
        BeatReport beatReport = beatReportService.getBeatReportById(beatReportId);
        beatReport.getBeatIllegalActivitiesReports().add(beatIllegalActivitiesReport);
        beatReportService.updateBeatReport(beatReport);
        return beatIllegalActivitiesReport;
    }

    @Transactional
    public BeatIllegalActivitiesReport getBeatIllegalActiviesReportById(Long id) {
        return beatIllegalActivitiesReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BeatExpectationsReport.class, id));
    }

//    @Transactional
//    public List<BeatReport> getBeatReportsByStartTimeIsBetween(Long startTime, Long endTime) {
//        List<BeatReport> beatReports = beatReportRepository.findBeatReportsByStartTimeIsBetween(startTime, endTime);
//        if (beatReports.isEmpty()) {
//            throw new EntityNotFoundException(BeatReport.class, startTime);
//        }
//        return beatReports;
//    }
}

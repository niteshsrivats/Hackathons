package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.BeatExpectationsReport;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.repositories.BeatExpectationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatExpectionsReportService {

    private final BeatExpectationReportRepository beatExpectationReportRepository;
    private final BeatReportService beatReportService;

    @Autowired
    public BeatExpectionsReportService(BeatExpectationReportRepository beatExpectationReportRepository, BeatReportService beatReportService) {
        this.beatExpectationReportRepository = beatExpectationReportRepository;
        this.beatReportService = beatReportService;
    }

    @Transactional
    public BeatExpectationsReport addBeatExpectationsReport(Long beatReportId, BeatExpectationsReport beatExpectationsReport) {
        beatExpectationsReport = beatExpectationReportRepository.save(beatExpectationsReport);
        BeatReport beatReport = beatReportService.getBeatReportById(beatReportId);
        beatReport.getBeatExpectationsReports().add(beatExpectationsReport);
        beatReportService.updateBeatReport(beatReport);
        return beatExpectationsReport;
    }

    @Transactional
    public BeatExpectationsReport getBeatExpectationsReportById(Long id) {
        return beatExpectationReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BeatExpectationsReport.class, id));
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

package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.BeatExpectationsReport;
import beatmanagement.central.models.entities.BeatLawOrderIssuesReport;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.repositories.BeatLawOrderIssuesReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatLawOrderIssuesReportService {

    private final BeatLawOrderIssuesReportRepository beatLawOrderIssuesReportRepository;
    private final BeatReportService beatReportService;

    @Autowired
    public BeatLawOrderIssuesReportService(BeatLawOrderIssuesReportRepository beatLawOrderIssuesReportRepository, BeatReportService beatReportService) {
        this.beatLawOrderIssuesReportRepository = beatLawOrderIssuesReportRepository;
        this.beatReportService = beatReportService;
    }

    @Transactional
    public BeatLawOrderIssuesReport addBeatExpectationsReport(Long beatReportId, BeatLawOrderIssuesReport beatLawOrderIssuesReport) {
        beatLawOrderIssuesReport = beatLawOrderIssuesReportRepository.save(beatLawOrderIssuesReport);
        BeatReport beatReport = beatReportService.getBeatReportById(beatReportId);
        beatReport.getBeatLawOrderIssuesReports().add(beatLawOrderIssuesReport);
        beatReportService.updateBeatReport(beatReport);
        return beatLawOrderIssuesReport;
    }

    @Transactional
    public BeatLawOrderIssuesReport getBeatLawOrderIssuesReportById(Long id) {
        return beatLawOrderIssuesReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BeatExpectationsReport.class, id));
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

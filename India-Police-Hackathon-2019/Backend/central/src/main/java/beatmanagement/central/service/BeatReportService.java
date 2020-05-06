package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.BeatBook;
import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.repositories.BeatReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class BeatReportService {

    private final BeatReportRepository beatReportRepository;
    private final BeatBookService beatBookService;
    private final OfficerService officerService;

    @Autowired
    public BeatReportService(BeatReportRepository beatReportRepository, BeatBookService beatBookService, OfficerService officerService) {
        this.beatReportRepository = beatReportRepository;
        this.beatBookService = beatBookService;
        this.officerService = officerService;
    }

    @Transactional
    public BeatReport addBeatReport(Long officerId) {

        BeatReport beatReport = new BeatReport(null, null, null, null, System.currentTimeMillis(), null, officerService.getOfficerById(officerId));
        return beatReportRepository.save(beatReport);
    }

    @Transactional
    public BeatReport getBeatReportById(Long id) {
        return beatReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BeatReport.class, id));
    }

    @Transactional
    public List<BeatReport> getBeatReportsByStartTimeIsBetween(Long startTime, Long endTime) {
        List<BeatReport> beatReports = beatReportRepository.findBeatReportsByStartTimeIsBetween(startTime, endTime);
        if (beatReports.isEmpty()) {
            throw new EntityNotFoundException(BeatReport.class, startTime);
        }
        return beatReports;
    }

    @Transactional
    public List<BeatReport> getAll() {
        return beatReportRepository.findAll();
    }

//    @Transactional
//    public void updateBeatReportLocation(Long beatReportId, List<Double> newLocations) {
//        BeatReport beatReport = getBeatReportById(beatReportId);
//        beatReport.getBeatLocations().addAll(newLocations);
//        beatReportRepository.save(beatReport);
//    }

    @Transactional
    public void updateBeatReport(BeatReport beatReport) {
        beatReportRepository.save(beatReport);
    }

    @Transactional
    public void endBeat(Long beatReportId) {
        BeatReport beatReport = getBeatReportById(beatReportId);
        beatReport.setEndTime(System.currentTimeMillis());
        beatReportRepository.save(beatReport);
        BeatBook beatBook = beatBookService.getBeatBookByBeatId(beatReport.getOfficer().getBeat().getId());
        beatBook.getBeatReports().add(beatReport);
        beatBookService.updateBeatReport(beatBook);
    }
}

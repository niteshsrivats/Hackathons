package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatExpectationsReport;
import beatmanagement.central.service.BeatExpectionsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static beatmanagement.central.named.entities.Endpoints.BeatReports;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(BeatReports)
@CrossOrigin
public class BeatExpectationsReportController {

    private final BeatExpectionsReportService beatExpectionsReportService;

    @Autowired
    public BeatExpectationsReportController(BeatExpectionsReportService beatExpectionsReportService) {
        this.beatExpectionsReportService = beatExpectionsReportService;
    }

    @GetMapping(path = "{id}/expectations")
    public BeatExpectationsReport getBeatExpectationsReportById(@PathVariable @NotBlank Long id) {
        return beatExpectionsReportService.getBeatExpectationsReportById(id);
    }

//    @GetMapping(path =)
//    public List<BeatReport> getBeatsByStationId(
//            @RequestParam(value = "month") Time startTime,
//            @RequestParam(value = "citizenType") Time endTime
//    ) {
//        return citizenService.getCitizensByBeatAndCitizenType(beatId, citizenType);
//    }

    @PostMapping(path = "{beatReportId}/expectations")
    public BeatExpectationsReport addBeat(
            @PathVariable @NotBlank Long beatReportId,
            @RequestBody @Valid @NotNull BeatExpectationsReport beatExpectationsReport) {
        return beatExpectionsReportService.addBeatExpectationsReport(beatReportId, beatExpectationsReport);
    }
}

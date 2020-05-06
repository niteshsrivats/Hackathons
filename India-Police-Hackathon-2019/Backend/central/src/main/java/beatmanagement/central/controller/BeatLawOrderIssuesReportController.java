package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatLawOrderIssuesReport;
import beatmanagement.central.service.BeatLawOrderIssuesReportService;
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
public class BeatLawOrderIssuesReportController {

    private final BeatLawOrderIssuesReportService beatLawOrderIssuesReportService;

    @Autowired
    public BeatLawOrderIssuesReportController(BeatLawOrderIssuesReportService beatLawOrderIssuesReportService) {
        this.beatLawOrderIssuesReportService = beatLawOrderIssuesReportService;
    }

    @GetMapping(path = "{beatReportId}/law-order")
    public BeatLawOrderIssuesReport getBeatExpectationsReportById(@PathVariable @NotBlank Long beatReportId) {
        return beatLawOrderIssuesReportService.getBeatLawOrderIssuesReportById(beatReportId);
    }

//    @GetMapping(path =)
//    public List<BeatReport> getBeatsByStationId(
//            @RequestParam(value = "month") Time startTime,
//            @RequestParam(value = "citizenType") Time endTime
//    ) {
//        return citizenService.getCitizensByBeatAndCitizenType(beatId, citizenType);
//    }

    @PostMapping(path = "{beatReportId}/law-order")
    public BeatLawOrderIssuesReport addBeat(
            @PathVariable @NotBlank Long beatReportId,
            @RequestBody @Valid @NotNull BeatLawOrderIssuesReport beatLawOrderIssuesReport) {
        return beatLawOrderIssuesReportService.addBeatExpectationsReport(beatReportId, beatLawOrderIssuesReport);
    }
}

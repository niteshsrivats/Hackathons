package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatIllegalActivitiesReport;
import beatmanagement.central.service.BeatIllegalActivitiesReportService;
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
public class BeatIllegalActivitiesReportController {

    private final BeatIllegalActivitiesReportService beatIllegalActivitiesReportService;

    @Autowired
    public BeatIllegalActivitiesReportController(BeatIllegalActivitiesReportService beatIllegalActivitiesReportService) {
        this.beatIllegalActivitiesReportService = beatIllegalActivitiesReportService;
    }

    @GetMapping(path = "{id}/illegal-activities")
    public BeatIllegalActivitiesReport getBeatIllegalActivitiesReport(@PathVariable @NotBlank Long id) {
        return beatIllegalActivitiesReportService.getBeatIllegalActiviesReportById(id);
    }

//    @GetMapping(path =)
//    public List<BeatReport> getBeatsByStationId(
//            @RequestParam(value = "month") Time startTime,
//            @RequestParam(value = "citizenType") Time endTime
//    ) {
//        return citizenService.getCitizensByBeatAndCitizenType(beatId, citizenType);
//    }

    @PostMapping(path = "{beatReportId}/illegal-activities")
    public BeatIllegalActivitiesReport addBeat(
            @PathVariable @NotBlank Long beatReportId,
            @RequestBody @Valid @NotNull BeatIllegalActivitiesReport beatIllegalActivitiesReport) {
        return beatIllegalActivitiesReportService.addBeatIllegalActivitiesReport(beatReportId, beatIllegalActivitiesReport);
    }
}

package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatReport;
import beatmanagement.central.service.BeatReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static beatmanagement.central.named.entities.Endpoints.BeatReports;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(BeatReports)
@CrossOrigin
public class BeatReportController {

    private final BeatReportService beatReportService;

    @Autowired
    public BeatReportController(BeatReportService beatReportService) {
        this.beatReportService = beatReportService;
    }

    @GetMapping(path = Id)
    public BeatReport getBeatReportById(@PathVariable @NotBlank Long id) {
        BeatReport beatReport = beatReportService.getBeatReportById(id);
//        beatReport.getBeatLocations();
        return beatReport;
    }

//    @GetMapping(path =)
//    public List<BeatReport> getBeatsByStationId(
//            @RequestParam(value = "month") Time startTime,
//            @RequestParam(value = "citizenType") Time endTime
//    ) {
//        return citizenService.getCitizensByBeatAndCitizenType(beatId, citizenType);
//    }

    @PostMapping(path = "{officerId}")
    public BeatReport addBeat(
            @PathVariable @NotBlank Long officerId
    ) {
        return beatReportService.addBeatReport(officerId);
    }
//
//    @PatchMapping(path = "{id}/locations")
//    public void updateBeatReportLocations(
//            @PathVariable @NotBlank Long id,
//            @RequestBody @Valid @NotNull List<Double> locations) {
//        beatReportService.updateBeatReportLocation(id, locations);
//    }

    @PatchMapping(path = "{id}/end")
    public void endBeatReport(@PathVariable @NotBlank Long id) {
        beatReportService.endBeat(id);
    }
}

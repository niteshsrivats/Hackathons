package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatLocation;
import beatmanagement.central.service.BeatLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static beatmanagement.central.named.entities.Endpoints.BeatLocations;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(BeatLocations)
@CrossOrigin
public class BeatLocationController {

    private final BeatLocationService beatLocationService;

    @Autowired
    public BeatLocationController(BeatLocationService beatLocationService) {
        this.beatLocationService = beatLocationService;
    }

    @PostMapping(path = Id)
    public void addBeatLocations(
            @PathVariable @NotBlank Long id,
            @RequestBody @Valid @NotNull BeatLocation beatLocation
    ) {
        beatLocationService.addBeatLocations(id, beatLocation);
    }

    @GetMapping(path = "live")
    public Map<String, ArrayList<Double>> getLocations() {
        return beatLocationService.getLocation();
    }

    @GetMapping(path = Id)
    public List<BeatLocation> getLocations(@PathVariable @NotBlank Long id) {
        return beatLocationService.getBeatLocationsByBeatReportId(id);
    }
}

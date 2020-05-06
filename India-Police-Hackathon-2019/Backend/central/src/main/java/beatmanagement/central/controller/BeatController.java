package beatmanagement.central.controller;

import beatmanagement.central.models.entities.Beat;
import beatmanagement.central.service.BeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static beatmanagement.central.named.entities.Endpoints.Beats;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(Beats)
@CrossOrigin
public class BeatController {
    private final BeatService beatService;

    @Autowired
    public BeatController(BeatService beatService) {
        this.beatService = beatService;
    }

    @GetMapping(path = Id)
    public Beat getBeatById(@PathVariable @NotBlank Long id) {
        return beatService.getBeatById(id);
    }

    @GetMapping(path = "station/{stationId}")
    public List<Beat> getBeatsByStationId(@PathVariable @NotBlank Long stationId) {
        return beatService.getBeatsByStationId(stationId);
    }

    @PostMapping
    public Beat addBeat(@RequestBody @Valid @NotNull Beat beat) {
        return beatService.addBeat(beat);
    }
}

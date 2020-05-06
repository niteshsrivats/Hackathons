package beatmanagement.central.controller;

import beatmanagement.central.models.entities.Station;
import beatmanagement.central.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static beatmanagement.central.named.entities.Endpoints.Stations;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(Stations)
@CrossOrigin
public class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(path = Id)
    public Station getStation(@PathVariable @NotBlank Long id) {
        return stationService.getStation(id);
    }

    @PostMapping
    public Station addStation(@RequestBody @Valid @NotNull Station beat) {
        return stationService.addStation(beat);
    }
}

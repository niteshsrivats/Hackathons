package beatmanagement.central.controller;

import beatmanagement.central.models.constants.OfficerType;
import beatmanagement.central.models.entities.Officer;
import beatmanagement.central.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static beatmanagement.central.named.entities.Endpoints.Officers;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(Officers)
@CrossOrigin
public class OfficerController {

    private final OfficerService officerService;

    @Autowired
    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @GetMapping(path = Id)
    public Officer getOfficerById(@PathVariable @NotBlank Long id) {
        return officerService.getOfficerById(id);
    }

    @GetMapping(path = "station/{stationId}")
    public List<Officer> getBeatsByStationId(
            @PathVariable @NotBlank Long stationId,
            @RequestParam(value = "officerType") OfficerType officerType
    ) {
        return officerService.getOfficersByStationAndOfficerType(stationId, officerType);
    }

    @PostMapping
    public Officer addOfficer(@RequestBody @Valid @NotNull Officer officer) {
        return officerService.addOfficer(officer);
    }

    @PutMapping(path = "{officerId}")
    public Officer updateOfficer(
            @PathVariable @NotBlank Long officerId,
            @RequestBody @Valid @NotNull List<Long> beatId) {
        if (beatId.isEmpty()) {
            return officerService.updateOfficer(officerId, null);
        }
        return officerService.updateOfficer(officerId, beatId.get(0));
    }
}

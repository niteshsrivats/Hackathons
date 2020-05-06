package beatmanagement.central.controller;

import beatmanagement.central.models.constants.CitizenType;
import beatmanagement.central.models.entities.Citizen;
import beatmanagement.central.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static beatmanagement.central.named.entities.Endpoints.Citizens;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(Citizens)
@CrossOrigin
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping(path = Id)
    public Citizen getCitizenById(@PathVariable @NotBlank Long id) {
        return citizenService.getCitizenById(id);
    }

    @GetMapping(path = "beat/{beatId}")
    public List<Citizen> getBeatsByStationId(
            @PathVariable @NotBlank Long beatId,
            @RequestParam(value = "citizenType") CitizenType citizenType
    ) {
        return citizenService.getCitizensByBeatAndCitizenType(beatId, citizenType);
    }

    @PostMapping
    public Citizen addBeat(@RequestBody @Valid @NotNull Citizen citizen) {
        return citizenService.addCitizen(citizen);
    }
}

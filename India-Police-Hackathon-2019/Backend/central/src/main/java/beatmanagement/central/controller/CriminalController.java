package beatmanagement.central.controller;

import beatmanagement.central.models.entities.Criminal;
import beatmanagement.central.service.CriminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static beatmanagement.central.named.entities.Endpoints.Criminals;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */


@RestController
@RequestMapping(Criminals)
@CrossOrigin
public class CriminalController {

    private final CriminalService criminalService;

    @Autowired
    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    @PostMapping
    public Criminal addCrime(
            @RequestBody @Valid @NotNull Criminal criminal
    ) {
        return criminalService.addCriminal(criminal);
    }

    @GetMapping(path = "beat/{id}")
    public List<Criminal> getCriminalByBeatId(@PathVariable @NotBlank Long id) {
        return criminalService.getCriminalsByBeatId(id);
    }

    @GetMapping(path = "data")
    public Map<String, Integer> getCrimeCountByBeatId() {
        return criminalService.getCriminalData();
    }
}

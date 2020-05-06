package beatmanagement.central.controller;

import beatmanagement.central.models.entities.Crime;
import beatmanagement.central.service.CrimeService;
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
public class CrimeController {
    private final CrimeService crimeService;

    @Autowired
    public CrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @PostMapping(path = "{id}/crimes")
    public Crime addCrime(
            @PathVariable @NotBlank Long id,
            @RequestBody @Valid @NotNull Crime crime
    ) {
        return crimeService.addCrime(id, crime);
    }
}

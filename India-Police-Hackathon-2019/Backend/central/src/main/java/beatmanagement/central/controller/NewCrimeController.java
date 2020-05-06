package beatmanagement.central.controller;

import beatmanagement.central.models.entities.Crime;
import beatmanagement.central.service.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

import static beatmanagement.central.named.entities.Endpoints.Crimes;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */
@RestController
@RequestMapping(Crimes)
@CrossOrigin
public class NewCrimeController {

    private final CrimeService crimeService;

    @Autowired
    public NewCrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @GetMapping(path = Id)
    public List<Crime> getCrimesByBeatId(@PathVariable @NotBlank Long id) {
        return crimeService.getCrimeByBeatId(id);
    }

    @GetMapping(path = "{id}/counts")
    public Integer getCrimeCountByBeatId(@PathVariable @NotBlank Long id) {
        return crimeService.getCrimeCountByBeatId(id);
    }

    @GetMapping(path = "data")
    public Map<Long, Integer> getData() {
        return crimeService.getData();
    }
}

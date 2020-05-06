package beatmanagement.central.controller;

import beatmanagement.central.models.entities.BeatBook;
import beatmanagement.central.service.BeatBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static beatmanagement.central.named.entities.Endpoints.BeatBooks;
import static beatmanagement.central.named.entities.Placeholders.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@RequestMapping(BeatBooks)
@CrossOrigin
public class BeatBookController {
    private final BeatBookService beatBookService;

    @Autowired
    public BeatBookController(BeatBookService beatBookService) {
        this.beatBookService = beatBookService;
    }

    @GetMapping(path = Id)
    public BeatBook getBeatBookById(@PathVariable @NotBlank Long id) {
        return beatBookService.getBeatBookById(id);
    }

    @GetMapping(path = "beat/{beatId}")
    public BeatBook getBeatBookByBeatId(@PathVariable @NotBlank Long beatId) {
        return beatBookService.getBeatBookByBeatId(beatId);
    }
}

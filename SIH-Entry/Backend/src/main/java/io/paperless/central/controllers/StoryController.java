package io.paperless.central.controllers;

import io.paperless.central.models.Story;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class StoryController {

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping(Endpoints.Stories.Base)
    public List<Story> getStories() {
        return storyService.getStories();
    }

    @GetMapping(Endpoints.Stories.StoryById)
    public Story getStoryById(@PathVariable @NotNull Long id) {
        return storyService.getStoryById(id);
    }

    @PostMapping(Endpoints.Stories.Base)
    public Story addStory(@RequestBody @Valid @NotNull Story story) {
        return storyService.addStory(story);
    }

    @PatchMapping(Endpoints.Stories.StoryById)
    public Story completeStory(@PathVariable @NotNull Long id) {
        return storyService.completeStory(id);
    }

    @DeleteMapping(Endpoints.Stories.StoryById)
    public void deleteStoryById(@PathVariable Long id) {
        storyService.deleteStoryById(id);
    }
}

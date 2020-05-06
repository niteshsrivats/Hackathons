package io.paperless.central.services;

import io.paperless.central.exceptions.DuplicateEntityException;
import io.paperless.central.exceptions.EntityNotFoundException;
import io.paperless.central.models.Story;
import io.paperless.central.models.Task;
import io.paperless.central.repositories.StoryRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Transactional
    public List<Story> getStories() {
        List<Story> stories = storyRepository.findAll();
        for (Story story : stories) {
            Hibernate.initialize(story.getFiles());
            for (Task task : story.getTasks()) {
                task.setFiles(null);
            }
        }
        return stories;
    }

    @Transactional
    public Story getStoryById(Long id) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Story.class, id));
        Hibernate.initialize(story.getFiles());
        for (Task task : story.getTasks()) {
            Hibernate.initialize(task.getFiles());
        }
        return story;
    }

    @Transactional
    public Story completeStory(Long id) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Story.class, id));
        Hibernate.initialize(story.getFiles());
        Hibernate.initialize(story.getTasks());
        for (Task task : story.getTasks()) {
            task.setStatus(true);
            Hibernate.initialize(task.getFiles());
        }
        story.setStatus(true);
        return storyRepository.save(story);
    }

    public Story addStory(Story story) {
        if (story.getId() == null) {
            return storyRepository.save(story);
        }
        try {
            getStoryById(story.getId());
            throw new DuplicateEntityException(Story.class, story.getId());
        } catch (EntityNotFoundException e) {
            return storyRepository.save(story);
        }
    }

    public void deleteStoryById(Long id) {
        getStoryById(id);
        storyRepository.deleteById(id);
    }
}

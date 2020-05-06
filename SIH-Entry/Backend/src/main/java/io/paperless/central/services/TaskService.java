package io.paperless.central.services;

import io.paperless.central.exceptions.DuplicateEntityException;
import io.paperless.central.exceptions.EntityNotFoundException;
import io.paperless.central.models.Story;
import io.paperless.central.models.Task;
import io.paperless.central.repositories.TaskRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            Hibernate.initialize(task.getFiles());
        }
        return tasks;
    }

    @Transactional
    public Task getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Task.class, id));
        Hibernate.initialize(task.getFiles());
        return task;
    }

    @Transactional
    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Story.class, id));
        task.setFiles(null);
        task.setStatus(true);
        return taskRepository.save(task);
    }

    public Task addTask(Task task) {
        if (task.getId() == null) {
            return taskRepository.save(task);
        }
        try {
            getTaskById(task.getId());
            throw new DuplicateEntityException(Task.class, task.getId());
        } catch (EntityNotFoundException e) {
            return taskRepository.save(task);
        }
    }

    public void deleteTaskById(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }
}

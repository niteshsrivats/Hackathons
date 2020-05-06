package io.paperless.central.controllers;

import io.paperless.central.models.Task;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(Endpoints.Tasks.Base)
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(Endpoints.Tasks.TaskById)
    public Task getTaskById(@PathVariable @NotNull Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping(Endpoints.Tasks.Base)
    public Task addTask(@RequestBody @Valid @NotNull Task task) {
        return taskService.addTask(task);
    }


    @PatchMapping(Endpoints.Tasks.TaskById)
    public Task completeTask(@PathVariable @NotNull Long id) {
        return taskService.completeTask(id);
    }

    @DeleteMapping(Endpoints.Tasks.TaskById)
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }
}

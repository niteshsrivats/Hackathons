package io.paperless.central.controllers;

import io.paperless.central.models.Process;
import io.paperless.central.models.Workflow;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class WorkflowController {

    private final WorkflowService workflowService;

    @Autowired
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping(Endpoints.Workflows.Base)
    public List<Workflow> getWorkflows() {
        return workflowService.getWorkflows();
    }

    @GetMapping(Endpoints.Workflows.WorkflowById)
    public Workflow getWorkflowById(@PathVariable @NotNull Long id) {
        return workflowService.getWorkflowById(id);
    }

    @GetMapping(Endpoints.Workflows.WorkflowProcess)
    public Process getWorkflowProcessTemplate(@PathVariable @NotNull Long id) {
        return workflowService.getTemplateProcess(id);
    }

    @PostMapping(Endpoints.Workflows.Base)
    public Workflow addWorkflow(@RequestBody @Valid @NotNull Workflow workflow) {
        return workflowService.addWorkflow(workflow);
    }

    @PostMapping(Endpoints.Workflows.WorkflowProcess)
    public Workflow addWorkflowProcess(@PathVariable @NotNull Long id, @RequestBody @Valid @NotNull Process process) {
        return workflowService.addWorkflowProcess(id, process);
    }

    @DeleteMapping(Endpoints.Workflows.WorkflowById)
    public void deleteWorkflowById(@PathVariable Long id) {
        workflowService.deleteWorkflowById(id);
    }
}

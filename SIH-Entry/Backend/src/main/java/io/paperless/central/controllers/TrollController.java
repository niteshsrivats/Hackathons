package io.paperless.central.controllers;

import io.paperless.central.models.Workflow;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TrollController {

    private final WorkflowService workflowService;

    @Autowired
    public TrollController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping(Endpoints.OldHabits.Base)
    public List<Workflow> getWorkflows() {
        return workflowService.getCompleteWorkflows();
    }
}

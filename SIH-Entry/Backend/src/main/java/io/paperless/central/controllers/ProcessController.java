package io.paperless.central.controllers;

import io.paperless.central.models.Process;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class ProcessController {

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping(Endpoints.Processes.Base)
    public List<Process> getProcesses() {
        return processService.getProcesses();
    }

    @GetMapping(Endpoints.Processes.ProcessById)
    public Process getProcessById(@PathVariable @NotNull Long id) {
        return processService.getProcessById(id);
    }

    @PostMapping(Endpoints.Processes.Base)
    public Process addProcess(@RequestBody @Valid @NotNull Process process) {
        return processService.addProcess(process);
    }

    @DeleteMapping(Endpoints.Processes.ProcessById)
    public void deleteProcessById(@PathVariable Long id) {
        processService.deleteProcessById(id);
    }
}

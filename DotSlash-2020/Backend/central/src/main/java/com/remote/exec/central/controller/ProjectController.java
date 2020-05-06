package com.remote.exec.central.controller;


import com.remote.exec.central.models.entities.Project;
import com.remote.exec.central.models.entities.Submission;
import com.remote.exec.central.named.Endpoints;
import com.remote.exec.central.security.UserPrincipal;
import com.remote.exec.central.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(Endpoints.Projects.Id)
    public Project getProjectById(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable @NotBlank String id) {
        return projectService.getProjectById(userPrincipal.getUser(), id);
    }

    @GetMapping(Endpoints.Projects.Submissions)
    public List<Submission> getProjectSubmissions(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable @NotBlank String id) {
        return projectService.getProjectSubmissionsById(userPrincipal.getUser(), id);
    }

    @PostMapping(Endpoints.Projects.Base)
    public Project addProject(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid @NotNull Project project) {
        return projectService.addProject(userPrincipal.getUser(), project);
    }

    @PatchMapping(Endpoints.Projects.Id)
    public Project updateProjectStatus(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable @NotBlank String id, @RequestBody @Valid @NotNull Boolean status) {
        return projectService.updateProjectStatus(userPrincipal.getUser(), id, status);
    }
}

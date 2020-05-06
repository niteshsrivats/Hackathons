package com.remote.exec.central.controller;

import com.remote.exec.central.models.entities.Submission;
import com.remote.exec.central.named.Endpoints;
import com.remote.exec.central.security.UserPrincipal;
import com.remote.exec.central.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.remote.exec.central.named.Headers.APIKey;

@RestController
@CrossOrigin
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping(Endpoints.Submissions.Id)
    public Submission getSubmissionById(@PathVariable @NotBlank String id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping(Endpoints.Submissions.Base)
    public Submission addSubmission(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestHeader(APIKey) String apiKey,
            @RequestBody @Valid @NotNull Submission submission
    ) {
        return submissionService.addSubmission(userPrincipal.getUser(), apiKey, submission);
    }
}

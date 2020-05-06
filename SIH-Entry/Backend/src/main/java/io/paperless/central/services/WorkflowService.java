package io.paperless.central.services;

import io.paperless.central.exceptions.DuplicateEntityException;
import io.paperless.central.exceptions.EntityNotFoundException;
import io.paperless.central.models.Process;
import io.paperless.central.models.Story;
import io.paperless.central.models.Task;
import io.paperless.central.models.Workflow;
import io.paperless.central.repositories.WorkflowRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    @Autowired
    public WorkflowService(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    public List<Workflow> getWorkflows() {
        List<Workflow> workflows = workflowRepository.findAll();
        for (Workflow workflow : workflows) {
            for (Process process : workflow.getProcesses()) {
                process.setStories(null);
            }
        }
        return workflows;
    }

    @Transactional
    public List<Workflow> getCompleteWorkflows() {
        List<Workflow> workflows = workflowRepository.findAll();
        for (Workflow workflow : workflows) {
            for (Process process : workflow.getProcesses()) {
                for (Story story : process.getStories()) {
                    Hibernate.initialize(story.getFiles());
                    Hibernate.initialize(story.getTasks());
                    for (Task task : story.getTasks()) {
                        Hibernate.initialize(task.getFiles());
                    }
                }
            }
        }
        return workflows;
    }

    public Workflow getWorkflowById(Long id) {
        Workflow workflow = workflowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Workflow.class, id));
        for (Process process : workflow.getProcesses()) {
            process.setStories(null);
        }
        return workflow;
    }

    public Workflow addWorkflow(Workflow workflow) {
        if (workflow.getId() == null) {
            return workflowRepository.save(workflow);
        }
        try {
            getWorkflowById(workflow.getId());
            throw new DuplicateEntityException(Workflow.class, workflow.getId());
        } catch (EntityNotFoundException e) {
            return workflowRepository.save(workflow);
        }
    }

    @Transactional
    public Workflow addWorkflowProcess(Long id, Process process) {
        Workflow workflow = workflowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Workflow.class, id));
        workflow.getProcesses().add(process);
        workflow = workflowRepository.save(workflow);
        for (Process currentProcess : workflow.getProcesses()) {
            for (Story story : currentProcess.getStories()) {
                Hibernate.initialize(story.getTasks());
                Hibernate.initialize(story.getFiles());
                for (Task task : story.getTasks()) {
                    Hibernate.initialize(task.getFiles());
                }
            }
        }
        return workflow;
    }

    @Transactional(readOnly = true)
    public Process getTemplateProcess(Long id) {
        Workflow workflow = workflowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Workflow.class, id));
        Process templateProcess = workflow.getProcesses().iterator().next();
        templateProcess.setId(null);
        templateProcess.setStatus(null);
        templateProcess.setName(workflow.getName());
        for (Story story : templateProcess.getStories()) {
            story.setFiles(null);
            story.setId(null);
            story.setStatus(null);
            for (Task task : story.getTasks()) {
                task.setFiles(null);
                task.setStatus(null);
                task.setId(null);
            }
        }
        return templateProcess;
    }

    public void deleteWorkflowById(Long id) {
        getWorkflowById(id);
        workflowRepository.deleteById(id);
    }
}

package io.paperless.central.services;

import io.paperless.central.exceptions.DuplicateEntityException;
import io.paperless.central.exceptions.EntityNotFoundException;
import io.paperless.central.models.Process;
import io.paperless.central.models.Story;
import io.paperless.central.models.Task;
import io.paperless.central.repositories.ProcessRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public List<Process> getProcesses() {
        List<Process> processes = processRepository.findAll();
        for (Process process : processes) {
            for (Story story : process.getStories()) {
                story.setFiles(null);
                story.setTasks(null);
            }
        }
        return processes;
    }

    @Transactional
    public Process getProcessById(Long id) {
        Process process = processRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Process.class, id));
        for (Story story : process.getStories()) {
            Hibernate.initialize(story.getFiles());
            for (Task task : story.getTasks()) {
                task.setFiles(null);
            }
        }
        return process;
    }

    public Process addProcess(Process process) {
        if (process.getId() == null) {
            return processRepository.save(process);
        }
        try {
            getProcessById(process.getId());
            throw new DuplicateEntityException(Process.class, process.getId());
        } catch (EntityNotFoundException e) {
            return processRepository.save(process);
        }
    }

    public void deleteProcessById(Long id) {
        getProcessById(id);
        processRepository.deleteById(id);
    }
}

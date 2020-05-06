package com.remote.exec.central.service;

import com.remote.exec.central.exceptions.BadRequestException;
import com.remote.exec.central.exceptions.EntityNotFoundException;
import com.remote.exec.central.models.entities.Metric;
import com.remote.exec.central.models.entities.Project;
import com.remote.exec.central.models.entities.Submission;
import com.remote.exec.central.models.entities.User;
import com.remote.exec.central.repository.ProjectRepository;
import com.remote.exec.central.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project getProjectById(User user, String id) {
        for (Project project : user.getProjects()) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        throw new EntityNotFoundException(Project.class, id);
    }

    @Transactional
    public List<Submission> getProjectSubmissionsById(User user, String id) {
        for (Project project : user.getProjects()) {
            if (project.getId().equals(id)) {
                Project dbProject = getProjectById(project.getId());
                return dbProject.getSubmissions();
            }
        }
        throw new EntityNotFoundException(Project.class, id);
    }

    @Transactional
    public Project getProjectById(String id) {
        if (id == null) {
            throw new BadRequestException("Project id cannot be null.");
        }
        Project project = projectRepository.findProjectById(id).orElseThrow(() -> new EntityNotFoundException(Project.class, id));
        Hibernate.initialize(project.getSubmissions());
        return project;
    }

    @Transactional
    public Project addProject(User user, Project project) {
        String userId = user.getId();
        user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
        project.setId(RandomStringUtils.random(5, true, true));
        project.setApiId(RandomStringUtils.random(10, true, true));
        project.setMetric(new Metric());
        user.getProjects().add(project);
        userRepository.save(user);
        return project;
    }

    @Transactional
    public Project updateProjectStatus(User user, String id, Boolean status) {
        for (Project project : user.getProjects()) {
            if (project.getId().equals(id)) {
                project.setStatus(status);
                Hibernate.initialize(project.getSubmissions());
                projectRepository.save(project);
                return project;
            }
        }
        throw new EntityNotFoundException(Project.class, id);
    }
}

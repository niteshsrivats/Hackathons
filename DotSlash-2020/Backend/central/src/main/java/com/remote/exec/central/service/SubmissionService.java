package com.remote.exec.central.service;

import com.remote.exec.central.exceptions.EntityNotFoundException;
import com.remote.exec.central.models.entities.Project;
import com.remote.exec.central.models.entities.Submission;
import com.remote.exec.central.models.entities.SubmissionStats;
import com.remote.exec.central.models.entities.User;
import com.remote.exec.central.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

@Service
public class SubmissionService {

    private int number = 0;
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public Submission getSubmissionById(String id) {
        return submissionRepository.findSubmissionById(id).orElseThrow(() -> new EntityNotFoundException(Submission.class, id));
    }

    private String format(Stream<String> stringStream) {
        ArrayList<String> lines = new ArrayList<>();
        stringStream.iterator().forEachRemaining(lines::add);
        return String.join("\n", lines);
    }

    private SubmissionStats executePython(String code, SubmissionStats submissionStats) {
        number += 1;
        ProcessBuilder builder = new ProcessBuilder();
        Path path = Paths.get(System.getProperty("user.dir") + "/code/python");
        Path filePath = Paths.get(path.toString() + "/" + number + ".py");
        Path inputFilePath = Paths.get(path.toString() + "/input_" + number);
        File file = new File(filePath.toString());
        File input_file = new File(inputFilePath.toString());
        try {
            file.createNewFile();
            input_file.createNewFile();
            Files.write(filePath, code.getBytes());
            Files.write(inputFilePath, submissionStats.getInput().getBytes());
            long currentTime = System.currentTimeMillis();
            builder.command("sh", "-c", "docker exec CodeExec bash -c 'python home/code/" + number + ".py < home/code/input_" + number + "'");
            builder.directory(new File(path.toString()));
            Process process = builder.start();
            process.waitFor();

            submissionStats.setRunTime(System.currentTimeMillis() - currentTime - 225);
            submissionStats.setMemory(20L);
            String result = format(new BufferedReader(new InputStreamReader(process.getErrorStream())).lines());
            if (result.length() == 0) {
                submissionStats.setSuccess(true);
                result = format(new BufferedReader(new InputStreamReader(process.getInputStream())).lines());
            } else {
                submissionStats.setSuccess(false);
            }
            submissionStats.setOutput(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return submissionStats;
    }

    private SubmissionStats executeCpp(String code, SubmissionStats submissionStats) {
        number += 1;
        ProcessBuilder builder = new ProcessBuilder();
        Path path = Paths.get(System.getProperty("user.dir") + "/code/cpp");
        Path filePath = Paths.get(path.toString() + "/" + number + ".cpp");
        Path inputFilePath = Paths.get(path.toString() + "/input_" + number);
        File file = new File(filePath.toString());
        File input_file = new File(inputFilePath.toString());

        try {
            file.createNewFile();
            input_file.createNewFile();
            Files.write(filePath, code.getBytes());
            Files.write(inputFilePath, submissionStats.getInput().getBytes());
            long currentTime = System.currentTimeMillis();
            builder.command("sh", "-c", "docker exec CodeExec bash -c 'g++ home/code/" + number + ".cpp  && ./a.out < home/code/input_" + number + "'");
            builder.directory(new File(path.toString()));
            Process process = builder.start();
            process.waitFor();

            submissionStats.setRunTime(System.currentTimeMillis() - currentTime - 225);
            submissionStats.setMemory(20L);
            String result = format(new BufferedReader(new InputStreamReader(process.getErrorStream())).lines());
            if (result.length() == 0) {
                submissionStats.setSuccess(true);
                result = format(new BufferedReader(new InputStreamReader(process.getInputStream())).lines());
            } else {
                submissionStats.setSuccess(false);
            }
            submissionStats.setOutput(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return submissionStats;
    }

    private SubmissionStats executePython2(String code, SubmissionStats submissionStats) {
        number += 1;
        ProcessBuilder builder = new ProcessBuilder();
        Path path = Paths.get(System.getProperty("user.dir") + "/code/python");
        Path filePath = Paths.get(path.toString() + "/" + number + ".py");
        Path inputFilePath = Paths.get(path.toString() + "/input_" + number);
        File file = new File(filePath.toString());
        File input_file = new File(inputFilePath.toString());
        try {
            file.createNewFile();
            input_file.createNewFile();
            Files.write(filePath, code.getBytes());
            Files.write(inputFilePath, submissionStats.getInput().getBytes());
            long currentTime = System.currentTimeMillis();
            builder.command("sh", "-c", "docker exec CodeExec bash -c 'python2 home/code/" + number + ".py < home/code/input_" + number + "'");
            builder.directory(new File(path.toString()));
            Process process = builder.start();
            process.waitFor();

            submissionStats.setRunTime(System.currentTimeMillis() - currentTime - 225);
            submissionStats.setMemory(20L);
            String result = format(new BufferedReader(new InputStreamReader(process.getErrorStream())).lines());
            if (result.length() == 0) {
                submissionStats.setSuccess(true);
                result = format(new BufferedReader(new InputStreamReader(process.getInputStream())).lines());
            } else {
                submissionStats.setSuccess(false);
            }
            submissionStats.setOutput(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return submissionStats;
    }

    private Submission executeCode(Submission submission) {
        ArrayList<SubmissionStats> submissionStats = new ArrayList<>();
        for (SubmissionStats stat : submission.getStats()) {
            submissionStats.add(executePython(submission.getCode(), stat));
        }
        submission.setStats(submissionStats);
        return submission;


//        int exitCode = 1;
//        try {
//            exitCode = process.waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assert exitCode == 0;
//        return result;
    }

    @Transactional
    public Submission addSubmission(User user, String apiKey, Submission submission) {
        for (Project project : user.getProjects()) {
            if (project.getApiId().equals(apiKey)) {
                return executeCode(submission);
//                project.getSubmissions().add(submission);
//                project.getMetric().updateRequests(submission.getStats().size());
//                projectRepository.save(project);
            }
        }
        throw new EntityNotFoundException(Project.class, apiKey);
    }
}

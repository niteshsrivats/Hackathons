package io.paperless.central.controllers;

import io.paperless.central.models.File;
import io.paperless.central.named.Endpoints;
import io.paperless.central.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

//    @GetMapping(Endpoints.Files.Base)
//    public List<File> getFiles() {
//        return fileService.getFiles();
//    }

    @GetMapping(Endpoints.Files.Base)
    public List<File> getDriveFiles() {
        return fileService.getFiles();
    }

    @GetMapping(Endpoints.Files.FileById)
    public File getFiles(@PathVariable @NotNull Long id) {
        return fileService.getFileById(id);
    }

    @PostMapping(Endpoints.Files.Base)
    public File addProcess(@RequestBody @Valid @NotNull File file) {
        return fileService.addFile(file);
    }

    @DeleteMapping(Endpoints.Files.FileById)
    public void deleteProcessById(@PathVariable Long id) {
        fileService.deleteFileById(id);
    }
}

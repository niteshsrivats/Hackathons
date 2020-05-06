package io.paperless.central.services;


import io.paperless.central.exceptions.DuplicateEntityException;
import io.paperless.central.exceptions.EntityNotFoundException;
import io.paperless.central.models.File;
import io.paperless.central.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(File.class, id));
    }

    public File addFile(File file) {
        if (file.getId() == null) {
            return fileRepository.save(file);
        }
        try {
            getFileById(file.getId());
            throw new DuplicateEntityException(File.class, file.getId());
        } catch (EntityNotFoundException e) {
            return fileRepository.save(file);
        }
    }

    public void deleteFileById(Long id) {
        getFileById(id);
        fileRepository.deleteById(id);
    }
}

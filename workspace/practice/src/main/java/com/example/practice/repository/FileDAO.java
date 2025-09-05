package com.example.practice.repository;

import com.example.practice.dto.FileDTO;
import com.example.practice.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void save(FileDTO fileDTO) { fileMapper.insertFile(fileDTO); }

    public void delete(Long id) { fileMapper.deleteFile(id); }

    public Optional<FileDTO> findById(Long id) { return fileMapper.selectFileById(id); }
}

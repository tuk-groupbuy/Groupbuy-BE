package com.example.tugether_be.post.service.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Service
public class FileStorageService {

    private final String uploadDir = "uploads"; // 상대 경로 (로컬 저장소)

    public String storeFile(MultipartFile file) {
        // 파일명 클린
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // 중복 방지를 위한 고유 이름 생성
        String extension = FilenameUtils.getExtension(fileName);
        String baseName = FilenameUtils.getBaseName(fileName);
        String uniqueFileName = baseName + "_" + System.currentTimeMillis() + "." + extension;

        // 저장 경로 준비
        Path uploadPath = Paths.get(uploadDir);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
    }
}

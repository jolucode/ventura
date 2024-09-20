package com.vsoluciones.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentsController {


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/download")
    public Mono<ResponseEntity<FileSystemResource>> downloadFile2(@RequestParam("path") String filePath) {
        return Mono.fromCallable(() -> {
            // Ruta absoluta al archivo en el servidor
            File file = new File(filePath);

            if (!file.exists() || !file.isFile()) {
                throw new IOException("File not found");
            }

            // Determinar el tipo MIME del archivo
            String mimeType = Files.probeContentType(file.toPath());

            // Si no se puede determinar el tipo MIME, usar "application/octet-stream" por defecto
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            // Crear las cabeceras para el archivo
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(mimeType));
            headers.setContentDispositionFormData("attachment", file.getName());

            // Devolver el archivo como recurso
            FileSystemResource resource = new FileSystemResource(file);

            return ResponseEntity.ok().headers(headers).body(resource);
        }).onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }
}

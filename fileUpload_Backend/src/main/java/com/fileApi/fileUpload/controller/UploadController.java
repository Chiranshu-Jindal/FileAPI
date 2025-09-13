package com.fileApi.fileUpload.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileApi.fileUpload.model.FileModel;
import com.fileApi.fileUpload.repo.FileJPA;
import com.fileApi.fileUpload.service.FIleService;

@RestController
@RequestMapping("/file")
public class UploadController {

	@Autowired
	FileJPA repo;

	@Autowired
	FIleService service;

	@PostMapping
	private FileModel uploadfile(@RequestBody FileModel file) {
		// this is for uploading cloud/ drive links

		return service.uploadfile(file);
	}

	@PostMapping("/uploadResume")
	public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
		try {
			// this is for uploading pdf and write it on server then get on any api
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			String uploadDir = "/home/chiranshu/upload/";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			// Save file to server
			Path filePath = uploadPath.resolve(fileName);
			Files.write(filePath, file.getBytes());

			// Save fileName/path in DB linked to user
			FileModel model = new FileModel();
			model.setFileName(fileName);
			model.setFileUrl(filePath.toString());

			FileModel saved = repo.save(model);

			return ResponseEntity.ok("Resume uploaded successfully: " + fileName);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
		}
	}

	@GetMapping
	private List<FileModel> getFile() {
		return service.getFile();
	}

	@GetMapping("/{id}")
	private FileModel getParticularFile(@PathVariable long id) {
		return service.getparticFile(id);
	}

	@PutMapping("/{id}")
	private FileModel updatingDetail(@PathVariable long id, @RequestBody FileModel file) {
		return service.updateFile(id, file);
	}

	@DeleteMapping("/{id}")
	private String deleteFile(@PathVariable long id) {
		service.deleteFile(id);
		return "Deleted Successfully";
	}

}
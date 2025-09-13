package com.fileApi.fileUpload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileApi.fileUpload.model.FileModel;
import com.fileApi.fileUpload.repo.FileJPA;

@Service
public class FIleService {

	@Autowired
	FileJPA repo;

	public FileModel uploadfile(FileModel model) {
		return repo.save(model);
	}

	public List<FileModel> getFile() {
		return repo.findAll();
	}

	public FileModel getparticFile(long id) {
		return repo.findById(id).orElse(null);
	}

	public FileModel updateFile(long id, FileModel filedetail) {
		return repo.findById(id).map(file -> {
			file.setFileName(filedetail.getFileName());
			file.setFileUrl(filedetail.getFileUrl());
			return repo.save(file);
		}).orElse(null);
	}

	public void deleteFile(long id) {
		repo.deleteById(id);
	}
}

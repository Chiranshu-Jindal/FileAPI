package com.fileApi.fileUpload.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class FileModel {

	@Id
	@Column(name = "id")
	long id;

	@Column(name = "filename")
	String fileName;

	@Column(name = "fileUrl")
	String fileUrl;

	public FileModel() {
		// TODO Auto-generated constructor stub
	}

	public FileModel(long id, String fileName, String fileUrl) {
		this.id = id;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}

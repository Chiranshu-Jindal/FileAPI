package com.fileApi.fileUpload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileApi.fileUpload.model.FileModel;

@Repository
public interface FileJPA extends JpaRepository<FileModel, Long> {

}

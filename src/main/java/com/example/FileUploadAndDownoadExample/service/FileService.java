package com.example.FileUploadAndDownoadExample.service;

import com.example.FileUploadAndDownoadExample.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public Boolean uploadFile(MultipartFile file) throws IOException;
    public byte[] downloadFile(String file) throws Exception;
    public boolean saveProduct(Product product);

    public String uploadFileWithData(MultipartFile file ) throws IOException;
}

package com.example.FileUploadAndDownoadExample.serviceImpl;

import com.example.FileUploadAndDownoadExample.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public Boolean uploadFile(MultipartFile file) throws IOException {
        String fileName= file.getOriginalFilename();
        File saveFile = new File(uploadPath);

        if(!saveFile.exists()){
            saveFile.mkdir();

        }
        String storePath = uploadPath.concat(fileName);
        long upload=Files.copy(file.getInputStream(), Paths.get(storePath));
         if(upload!= 0){
             return true;
         }
        return false;
    }

    @Override
    public byte[] downloadFile(String file) throws Exception {
        String fullPath = uploadPath.concat(file);
        try{
            InputStream ios= new FileInputStream(fullPath);
            return StreamUtils.copyToByteArray(ios);

        }catch(Exception e){
            e.printStackTrace();
            throw e;

        }


    }
}

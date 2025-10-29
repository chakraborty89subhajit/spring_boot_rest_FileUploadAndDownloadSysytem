package com.example.FileUploadAndDownoadExample.controller;

import com.example.FileUploadAndDownoadExample.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
        try{

            Boolean uploadFile = fileService.uploadFile(file);
            if(uploadFile){
                return new ResponseEntity<>("uploaded successfull",HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("upload field",HttpStatus.INTERNAL_SERVER_ERROR);
            }




        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

@GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String file) {
        try {
            byte[] downloadFile = fileService.downloadFile(file);
            String contentType = getContentType(file);
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDispositionFormData("attachment","file");
            return ResponseEntity.ok().headers(headers).body(downloadFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public String getContentType(String fileName){
        String extension = FilenameUtils.getExtension(fileName);

        switch(extension){
            case "pdf":
                return  "application/pdf";

            case "xlsx":
                return  "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";

            case "txt":
                return  "text/plain";

            case "png":
                return  "image/png";

            case "jpeg":
                return  "image/jpeg";

            default:
                return  "application/octet-stream";

        }

    }



}

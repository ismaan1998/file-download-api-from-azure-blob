package com.example.demo.controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


@RestController
public class FileDownload {
    @GetMapping(path="/download-file")
    public ResponseEntity<Resource> getFile(){
        //enter file name
        String fileName = "filename.txt";
        //azure credentials
        String connection_string = "connection string of your storage account ";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connection_string).buildClient();

        BlobContainerClient containerClient= blobServiceClient.getBlobContainerClient("container name");
        System.out.println(containerClient.getBlobContainerName());

        BlobClient blob = containerClient.getBlobClient(fileName);

        //creating an object of output stream to recieve the file's content from azure blob.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.download(outputStream);

        //converting it to the inputStream to return
        final byte[] bytes = outputStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(resource);
    }

}

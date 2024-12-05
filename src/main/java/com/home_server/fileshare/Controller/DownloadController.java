package com.home_server.fileshare.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;


@org.springframework.stereotype.Controller
public class DownloadController{
    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile() throws IOException{
        String filename="/home/souranil/Videos/video1.mkv";
        File file=new File(filename);
        InputStreamResource fileresource=new InputStreamResource(new FileInputStream(file));

        HttpHeaders header=new HttpHeaders();
        header.set("Content-Disposition", "attachment; filename="+file.getName());

        ResponseEntity<Object>  response=ResponseEntity.status(200).headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(fileresource);

        return response;
    }
}

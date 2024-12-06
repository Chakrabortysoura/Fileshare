package com.home_server.fileshare.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;

@Controller
public class DownloadController{
    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile() throws IOException{
        String filename="/home/souranil/Pictures/Mualani Build.png";
        File file=new File(filename);
        if(!file.exists()){
            ResponseEntity<Object> response=ResponseEntity.status(404).body("The File couldn't be at the desired location.\n");
            return response;
        }
        InputStreamResource fileresource=new InputStreamResource(new FileInputStream(file));

        HttpHeaders header=new HttpHeaders();
        header.set("Content-Disposition", "attachment; filename="+file.getName());

        ResponseEntity<Object>  response=ResponseEntity.status(200).headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(fileresource);

        return response;
    }
}

package com.home_server.fileshare.Controller;

import com.home_server.fileshare.Service.FileShareLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;

@Controller
public class DownloadController{
    @Autowired
    FileShareLinksService linksService;

    @GetMapping("/download/{name}")
    public ResponseEntity<Object> downloadFile(@PathVariable("name") String name) throws IOException{
        File file=new File(linksService.getFileLinks(name));
        InputStreamResource fileresource=new InputStreamResource(new FileInputStream(file));

        HttpHeaders header=new HttpHeaders();
        header.set("Content-Disposition", "attachment; filename="+file.getName());

        return ResponseEntity.status(200).headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(fileresource);
    }
    @GetMapping("/download/links")
    public String downloadLinksPage(Model model){
        if(linksService.getAllFileLinks()!=null){
            model.addAttribute("links",linksService.getAllFileLinks().keys());
        }
        return "DownloadPage";
    }
}

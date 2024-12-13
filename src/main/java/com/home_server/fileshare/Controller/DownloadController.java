package com.home_server.fileshare.Controller;

import com.home_server.fileshare.Service.FileShareLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@Controller
public class DownloadController{
    @Autowired
    FileShareLinksService linksService;

    @GetMapping("share/download/{name}")
    public ResponseEntity<Object> downloadFile(@PathVariable("name") String name) throws IOException{
        String filename=linksService.getFileLinks(name);
        File file=new File(filename);
        InputStreamResource fileresource=new InputStreamResource(new FileInputStream(file));

        HttpHeaders header=new HttpHeaders();
        header.set("Content-Disposition", "attachment; filename="+file.getName());

        ResponseEntity<Object>  response=ResponseEntity.status(200).headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(fileresource);

        return response;
    }
    @GetMapping("share/download/links")
    public ModelAndView downloadLinksPage(ModelAndView view){
        if(linksService.getAllFileLinks()!=null){
            view.addObject("links",linksService.getAllFileLinks().keys());
        }
        view.setViewName("DownloadPage");
        return view;
    }
}

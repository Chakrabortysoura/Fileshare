package com.home_server.fileshare.Controller;

import com.home_server.fileshare.Service.FileShareLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.Hashtable;


@Controller
public class Homepage{
    @Autowired
    private FileShareLinksService linksService;

    @GetMapping("/home")
    public ModelAndView homepage(ModelAndView view){
        if(linksService.getAllFileLinks()!=null){
            view.addObject("links",linksService.getAllFileLinks().keys());
        }
        view.setViewName("Homepage");
        return view;
    }

    @GetMapping("/")
    public RedirectView homepage(){
        return new RedirectView("/home");
    }


    @GetMapping("/share/add")
    public ModelAndView addFiletoShare(@RequestParam("filepath") String filepath, @RequestParam("name") String name, ModelAndView view){
        if(new File(filepath).exists()){
            if(linksService.addnewFileLink(name,filepath)){
                System.out.println("Filelink added successfully.");
            }
            else{System.out.println("Filelink already exsits.");}
        }
        if(linksService.getAllFileLinks()!=null){
            view.addObject("links",linksService.getAllFileLinks().keys());
        }
        view.setViewName("Homepage");
        return view;
    }

    @GetMapping("/share/delete")
    public ModelAndView deleteFilefromShare(@RequestParam("files") String[] name, ModelAndView view){
        Hashtable<String,String> links=linksService.deleteMultipleFileLinks(name);

        view.addObject("links",links.keys());
        view.setViewName("Homepage");
        return view;
    }

}

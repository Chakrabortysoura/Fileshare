package com.home_server.fileshare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Hashtable;

@Controller
public class Homepage{

    @GetMapping("/home")
    public String homepage(){
        return "Homepage";
    }

    @GetMapping("/share/add")
    public void addFiletoShare(@RequestParam("filepath") String filepath, @RequestParam("name") String name){

    }

}

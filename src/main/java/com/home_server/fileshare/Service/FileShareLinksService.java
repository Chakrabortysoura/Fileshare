package com.home_server.fileshare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class FileShareLinksService {
    @Autowired
    private Hashtable<String, String> sharedfiles;

    public boolean addnewFileLink(String name,String filepath){
        if(!sharedfiles.contains(name)){
            sharedfiles.put(name,filepath);
            return true;
        }
        return false;
    }

    public String getFileLink(String name){
        if(sharedfiles.contains(name)){
            return sharedfiles.get(name);
        }
        return null;
    }

    public Hashtable<String, String> getAllFileLinks(){
        if(!sharedfiles.isEmpty()){
            return new Hashtable<>(sharedfiles);
        }
        return null;
    }
}

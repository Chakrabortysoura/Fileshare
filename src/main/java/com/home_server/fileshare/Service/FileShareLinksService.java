package com.home_server.fileshare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.Hashtable;

@Service
public class FileShareLinksService {
    //Hashtable to hold the various paths for the files and name of the files to be shared
    private Hashtable<String, String> sharedfiles=new Hashtable<String,String>();

    public boolean addnewFileLink(String name,String filepath){
        if(!sharedfiles.containsKey(name)) {
            sharedfiles.put(name, filepath);
            return true;
        }
        return false;
    }

    public String getFileLinks(String name){
        if(sharedfiles.containsKey(name)){
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

    public Hashtable<String, String> deleteFileLink(String name){
        sharedfiles.remove(name);
        return sharedfiles;
    }

    public Hashtable<String, String> deleteMultipleFileLinks(String[] name){
        for(String i:name){
            sharedfiles.remove(i);
        }
        return sharedfiles;
    }
}

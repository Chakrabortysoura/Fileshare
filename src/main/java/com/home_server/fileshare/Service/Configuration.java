package com.home_server.fileshare.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    final static File config_file=new File("userdata.txt");
    public static void help(){
        String message="The arguments pattern for the command are in this order----->\n" +
                "fileshare <ARGUMENT> parameter1(optional) parameter2(optional\n)" +
                "Available ARGUMENTS:\n" +
                "config => Adding a new user. Example: fileshare -config 'username' 'passwd' " +
                "delete => Deleting the user credentials from the system. Example: fileshare -delete";
        System.out.println(message);
    }
    public static void configure(String... args) throws IOException{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(config_file));

                writer.write(args[0] + "\n");
                writer.write(passwordEncoder.encode(args[1]));

                writer.close();
            } finally {
                System.out.println("The command execution completed.");
            }
    }
    public static void delete(){
        if(config_file.exists()){
            config_file.delete();
        }
        System.out.println("The command execution completed");
    }
}

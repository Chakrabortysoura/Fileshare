package com.home_server.fileshare.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class UserConfigurationHelper {
    private final BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    private final File config_file=new File("/home/souranil/userdata.txt");

    public void help(){
        //Configuration helper message for users
        String message="The arguments pattern for the command are in this order----->\n" +
                "fileshare <ARGUMENT> parameter1(optional) parameter2(optional)\n" +
                "Available ARGUMENTS:\n" +
                "config => Adding a new user. Example: fileshare -config 'username' 'passwd' " +
                "delete => Deleting the user credentials from the system. Example: fileshare -delete";
        System.out.println(message);
    }

    public String getpassword() throws IOException{
        BufferedReader reader=new BufferedReader(new FileReader(config_file));
        reader.readLine();
        return reader.readLine();
    }

    public String getusername() throws IOException{
        BufferedReader reader=new BufferedReader(new FileReader(config_file));
        return reader.readLine();
    }

    public void configure(String... args) throws IOException{
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(config_file));
                writer.write(args[0] + "\n"+passwordEncoder.encode(args[1]));
                writer.close();
            } finally {
                System.out.println("Config file created with username and password.");
            }
    }

    public void setNewPasswd(String passwd) throws IOException{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config_file));
            writer.write(getusername()+ "\n"+passwordEncoder.encode(passwd));
            writer.close();
        } finally {
            System.out.println("New password setup completed.");
        }
    }

    public void delete(){
        if(config_file.exists()){
            config_file.delete();
        }
        System.out.println("Config file deleted.");
    }
}

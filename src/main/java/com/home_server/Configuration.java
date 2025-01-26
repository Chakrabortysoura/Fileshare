package com.home_server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    public static void help(){
        String message="The arguments pattern for the command are in this order----->\n" +
                "fileshare <ARGUMENT> parameter1(optional) parameter2(optional\n)" +
                "Available ARGUMENTS:\n" +
                "config => Adding a new user. Example: fileshare -config 'username' 'passwd' " +
                "delete => Deleting the user credentials from the system. Example: fileshare -delete";
        System.out.println(message);
    }
    public static void main(String[] args){
        final File file=new File("/home/souranil/userdata.txt");

        if(args[0].equals("config")){
            BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
            try{
                BufferedWriter writer=new BufferedWriter(new FileWriter(file));

                writer.write(args[1]+"\n");
                writer.write(passwordEncoder.encode(args[2]));

                writer.close();
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please provide the correct number of parameter.");
                help();
            }catch (IOException e){
                System.out.println("There was an error accessing the file.");
            }finally {
                System.out.println("The command execution completed.");
            }

        }

        else if(args[0].equals("delete")){
            if(file.exists()){
                file.delete();
            }
            System.out.println("The command execution completed");
        }

        else if(args[0].equals("help")){
            help();
        }
    }
}

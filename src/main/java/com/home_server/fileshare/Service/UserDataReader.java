package com.home_server.fileshare.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;

public class UserDataReader {
    private final File userdata=new File("/home/souranil/userdata.txt");

    public String getpassword() throws IOException{
            BufferedReader reader=new BufferedReader(new FileReader(userdata));
            reader.readLine();
            return reader.readLine();
    }

    public String getusername() throws IOException{
            BufferedReader reader=new BufferedReader(new FileReader(userdata));
            return reader.readLine();
    }
}

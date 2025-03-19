package com.home_server.fileshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.home_server.fileshare.Service.Configuration;

import java.io.IOException;

@SpringBootApplication
public class FileshareApplication {
	public static void main(String[] args) {
		try{
			if(args.length==0){
				System.out.println("No arguments are passed");
			}
			else{
				switch (args[0]){
					case "config": Configuration.configure(args[1], args[2]);
						break;
					case "delete": Configuration.delete();
						break;
					default: System.out.println("Improper parameters");
						Configuration.help();
				}
			}
		}catch (IOException e){
			System.out.println("There was some error accessing the config file");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Improper parameters");
			Configuration.help();
		}
		finally {
			SpringApplication.run(FileshareApplication.class, args);
		}
	}
}

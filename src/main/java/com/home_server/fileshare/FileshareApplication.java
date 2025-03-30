package com.home_server.fileshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.home_server.fileshare.Service.UserConfigurationHelper;

import java.io.IOException;

@SpringBootApplication
public class FileshareApplication {
	public static void main(String[] args) {
		UserConfigurationHelper user_config_helper=new UserConfigurationHelper();

		try{
			if(args.length==0){
				System.out.println("No arguments are passed");
			}
			else{
				switch (args[0]){
					case "--config": user_config_helper.configure(args[1], args[2]);
						break;
					case "--delete": user_config_helper.delete();
						break;
					default: System.out.println("Improper parameters");
						user_config_helper.help();
				}
			}
		}catch (IOException e){
			System.out.println("There was some error accessing the config file");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Improper parameters");
			user_config_helper.help();
		}
		finally {
			SpringApplication.run(FileshareApplication.class, args);
		}
	}
}

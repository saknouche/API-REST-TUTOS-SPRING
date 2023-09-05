package com.sadev.tuto;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TutoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutoManagementApplication.class, args);
//		openSwagger();
	}

//	   private static void openSwagger() {
//	        System.setProperty("java.awt.headless", "false");
//	        Desktop desktop = Desktop.getDesktop();
//	        try {
//	            desktop.browse(new URI("http://localhost:8080/swagger-ui/index.html"));
//	        }
//	        catch(Exception e) {
//	            System.out.println("ERROR LOADING PAGE");
//	        }
//	    }
}

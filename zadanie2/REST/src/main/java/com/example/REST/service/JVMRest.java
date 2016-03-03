package com.example.REST.service;

import java.io.File;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("JVM")
public class JVMRest {
	
	
	@GET
	@Path("/getClass")
	@Produces("text/plain")
	public Response getFile(){
		File file = null;
		Random rnd = new Random();
		switch(rnd.nextInt(3)){
			case 0: file = new File("classes/PawelServiceImpl.class"); break;
			case 1: file = new File("classes/PawelServiceImpl1.class"); break;
			case 2: file = new File("classes/PawelServiceImpl2.class");
		}
			
		
		
		ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"PawelServiceImpl.class\"");
        return response.build();
	}

}

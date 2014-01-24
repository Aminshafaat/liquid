package com.resource;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.dao.*;
import com.model.*;
import com.general.*;

@Path("/users")
public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	int id;
	public UserResource(UriInfo uriInfo, Request request, int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		 return "select * from user where email = 'aa@aaa.com' and password = '" + General.instance.SHA1("123") + "'";
		//return General.instance.SHA1("123");
		
	}
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public User login(
			@DefaultValue("") @QueryParam("email") String email,
			@DefaultValue("") @QueryParam("password") String password		
	) {
		  User user = null;
		 // email = "liquidbenchmark@gmail.com";
		//  password = "123";
		  user = UserDao.instance.login(email, password);
          
		if(user==null)
			throw new RuntimeException("GET: email or password is incorrect");
		return user;
	}
	@GET
	@Path("loginok")
	@Produces(MediaType.APPLICATION_JSON)
	public User login() {
		System.out.println("here");
		  User user = null;
		  String email = "liquidbenchmark@gmail.com";
		  String password = "123";
		  user = UserDao.instance.login(email, password);
          
		if(user==null)
			throw new RuntimeException("GET: email or password is incorrect");
		return user;
	}
}

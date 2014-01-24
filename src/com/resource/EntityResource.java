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

@Path("/entity")
public class EntityResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	int id;
	public EntityResource(UriInfo uriInfo, Request request, int id) {
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
	@Path("getdescription")
	@Produces({MediaType.APPLICATION_JSON})
    public String getDescription(@DefaultValue("1") @QueryParam("type") int type) {
			return EntityDao.instance.getEntityDescription(type);
    }
	@GET
	@Path("getentity")
	@Produces({MediaType.APPLICATION_JSON})
    public String getEntity(@QueryParam("id") int id) {
			return EntityDao.instance.getEntity(id);
    }
	@POST
	@Path("addentity")
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
    public String addEntity(@FormParam("parent") String parent, @FormParam("type") int type, 
    		@FormParam("title") String title, @FormParam("data") String data) {
		return EntityDao.instance.addEntity(parent, type, title, data);
			
    }
	

}

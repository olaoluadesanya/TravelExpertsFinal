package restservice;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Agent;
import model.Booking;
import model.Customer;
import model.Packag;
import model.Product;


@Path("/db")
public class SimpleRestService {

	private final transient Logger logger = Logger.getLogger(SimpleRestService.class);
	
	//http://localhost:8080/TravelExperts2/rs/db/getallcustomers

	@GET
	@Path("/getallcustomers")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAllCustomers(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select c from Customer c");
	                List<Customer> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Customer>>() {}.getType();
	                response = gson.toJson(list, type);

	                break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getSomething");
        }
        return response;	
	}
	
	//http://localhost:8080/TravelExperts2/rs/db/getallproducts
	
	@GET
	@Path("/getallproducts")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAllProducts(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select p from Product p");
	                List<Product> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Product>>() {}.getType();
	                response = gson.toJson(list, type);

	                break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getSomething");
        }
        return response;	
	}
	
	
	//http://localhost:8080/TravelExperts2/rs/db/getallpackages
	
	@GET
	@Path("/getallpackages")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllPackages(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select p from Packag p");
	                List<Packag> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Packag>>() {}.getType();
	                response = gson.toJson(list, type);
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getSomething");
        }
        return response;	
	}
	
	//http://localhost:8080/TravelExperts2/rs/db/getallbookings
	
	@GET
	@Path("/getallbookings")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAllBookings(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Query query = em.createQuery("select b from Booking b");
	                List<Booking> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Booking>>() {}.getType();
	                response = gson.toJson(list, type);
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getSomething");
        }
        return response;	
	}
	
	//http://localhost:8080/TravelExperts2/rs/db/getallagents
	
		@GET
		@Path("/getallagents")
	    @Produces(MediaType.TEXT_PLAIN)
		public String getAllAgents(@QueryParam("request") String request ,
				 @DefaultValue("1") @QueryParam("version") int version) {

			if (logger.isDebugEnabled()) {
				logger.debug("Start getSomething");
				logger.debug("data: '" + request + "'");
				logger.debug("version: '" + version + "'");
			}

			String response = null;

	        try{			
	            switch(version){
		            case 1:
		                if(logger.isDebugEnabled()) logger.debug("in version 1");
		                
		                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
		                EntityManager em = factory.createEntityManager();
		                
		                Query query = em.createQuery("select a from Agent a");
		                List<Agent> list = query.getResultList();
		                
		                Gson gson = new Gson();
		                Type type = new TypeToken<List<Agent>>() {}.getType();
		                response = gson.toJson(list, type);
		                
	                    break;
	                default: throw new Exception("Unsupported version: " + version);
	            }
	        }
	        catch(Exception e){
	        	response = e.getMessage().toString();
	        }
	        
	        if(logger.isDebugEnabled()){
	            logger.debug("result: '"+response+"'");
	            logger.debug("End getSomething");
	        }
	        return response;	
		}
	
	
	@POST
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String postSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postSomething");
        }
        return response;	
	}

	@PUT
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String putSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start putSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End putSomething");
        }
        return response;	
	}

	@DELETE
	@Path("/<add method name here>")
	public void deleteSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteSomething");
        }
	}
}

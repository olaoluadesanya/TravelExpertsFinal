package restservice;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import model.Agent;
import model.Booking;
import model.Bookingdetail;
import model.Clas;
import model.Customer;
import model.Fee;
import model.Packag;
import model.Product;
import model.Triptype;


@Path("/db")
public class SimpleRestService {

	private final transient Logger logger = Logger.getLogger(SimpleRestService.class);
	
	//http://localhost:8080/TravelExperts2/rs/db/getallcustomers

	@GET
	@Path("/getallcustomers")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
		
	//http://localhost:8080/TravelExperts2/rs/db/getallclasses	
	@GET
	@Path("/getallclasses")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllClasses(@QueryParam("request") String request ,
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
		                
	                Query query = em.createQuery("SELECT c FROM Clas c");
	                List<Clas> list = query.getResultList();
		                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Clas>>() {}.getType();
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
		
	//http://localhost:8080/TravelExperts2/rs/db/getallfees
	@GET
	@Path("/getallfees")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllFees(@QueryParam("request") String request ,
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
			                
	                Query query = em.createQuery("SELECT f FROM Fee f");
	                List<Fee> list = query.getResultList();
			                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Fee>>() {}.getType();
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
	
	//http://localhost:8080/TravelExperts2/rs/db/getalltriptypes
	@GET
	@Path("/getalltriptypes")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllTripTypes(@QueryParam("request") String request ,
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
			                
	                Query query = em.createQuery("SELECT t FROM Triptype t");
	                List<Triptype> list = query.getResultList();
			                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Triptype>>() {}.getType();
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
	
	//http://localhost:8080/TravelExperts2/rs/db/postpackage
	@POST
	@Path("/postpackage")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String postPackage(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Gson gson = new Gson();
	          	  	Packag packag = gson.fromJson(jsonString, Packag.class);
	                
	                em.getTransaction().begin();
	                em.persist(packag);
	                em.getTransaction().commit();
	                
	                response = "Package created";

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
	
	//http://localhost:8080/TravelExperts2/rs/db/postbooking
	@POST
	@Path("/postbooking")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String postBooking(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
		                
	                Gson gson = new Gson();
	          	  	Booking booking = gson.fromJson(jsonString, Booking.class);
	          	  	//add current date to booking object	
	          	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          	  	Date date = format.parse(format.format(new Date()));
	          	  	booking.setBookingDate(date);	
	          	  	//add customer id 
	          	  	Customer cust = gson.fromJson(jsonString, Customer.class);
	          	  	booking.setCustomer(cust);
	          	  	System.out.println("JSON = " + jsonString);	          	  	
	          	  	//add package id
	          	  	Packag pack = gson.fromJson(jsonString, Packag.class);
	          	  	booking.setPackag(pack);
	          	  	//add booking detail
	          	  	Bookingdetail detail = gson.fromJson(jsonString, Bookingdetail.class);	
	          	  	detail.setBooking(booking);
	          	  	List<Bookingdetail> detailsList = new ArrayList();
	          	  	detailsList.add(detail);
	          	    booking.setBookingdetails(detailsList);         	  	
	          	  	//insert booking
	                em.getTransaction().begin();
	                em.persist(booking);	                
	                em.getTransaction().commit();	                
	                
	                response = "Booking and Detail created";

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
	
	//http://localhost:8080/TravelExperts2/rs/db/agentlogin
	@POST
	@Path("/agentlogin")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticateAgent(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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
		                
	                JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
	          	  	String email = json.get("username").getAsString();
	          	  	String password = json.get("password").getAsString();          
	          	    
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();    
	          	  	Query query = em.createQuery("SELECT a.Pass FROM Agent a where a.agtEmail=?1");
	          	  	query.setParameter(1, email);	   
	          	  	
		          	List<String> list = query.getResultList();   
		          	if (list.isEmpty()) {
		          		response = "Username is incorrect";
		          	}
	          	  	else {
	          	  		String hashedPassword = list.get(0);
	          	  		boolean result = BCrypt.checkpw(password, hashedPassword);	          	  		
	          	  		if (result) {
	          	  			response = "IStrue";
	          	  		}
	          	  		
	          	  		else response = "IsFalse";	          	  		
	          	  	}
	          	  	
	          	  	
	                break;
	            default: throw new Exception("Unsupported version: " + version);
	        }
	    }
	    catch(Exception e){
	      	response = e.getMessage().toString();
	    }
	        
	    if(logger.isDebugEnabled()){
	        logger.debug("result: '"+response+"'");
	        logger.debug("End agentlogin");
	    }
	    return response;	
	}
	
	@PUT
	@Path("/putpackage")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String putPackage(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
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
	                
	                EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExperts2");
	                EntityManager em = factory.createEntityManager();
	                
	                Gson gson = new Gson();
	          	  	Packag newPackage = gson.fromJson(jsonString, Packag.class);
	          	  	
	          	  	Packag oldPackage = em.find(Packag.class, newPackage.getPackageId());
	                
	                em.getTransaction().begin();
	                oldPackage.setPackageId(newPackage.getPackageId());
	                oldPackage.setPkgAgencyCommission(newPackage.getPkgAgencyCommission());
	                oldPackage.setPkgBasePrice(newPackage.getPkgBasePrice());
	                oldPackage.setPkgDesc(newPackage.getPkgDesc());
	                oldPackage.setPkgEndDate(newPackage.getPkgEndDate());
	                oldPackage.setPkgName(newPackage.getPkgName());
	                oldPackage.setPkgStartDate(newPackage.getPkgStartDate());
	                em.getTransaction().commit();
	                
	                response = "Package updated";

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

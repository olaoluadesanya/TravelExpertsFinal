package restservice;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

import model.Agent;
import model.Booking;
import model.Customer;
import model.Packag;
import model.PackagesProductsSupplier;
import model.Product;
import model.ProductsSupplier;
import model.ProductsSuppliersReturn;
import model.Supplier;


@Path("/db")
public class SimpleRestService {

	private final transient Logger logger = Logger.getLogger(SimpleRestService.class);
	
	/*
	 * This block of code does crud operations on customers
	 */
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
	
	//http://localhost:8080/TravelExperts2/rs/db/getcustomer
	@GET
	@Path("/getcustomer/{ custid }")
    @Produces(MediaType.APPLICATION_JSON)
	public String getCustomer(@PathParam("custid") int custid,
			@QueryParam("request") String request ,
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
	                
	                Query query = em.createQuery("select c from Customer c where c.customerId=" + custid);
	                Customer cust = (Customer) query.getSingleResult();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<Customer>() {}.getType();
	                response = gson.toJson(cust, type);

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
	
	//http://localhost:8080/TravelExperts2/rs/db/insertcustomer
	@POST
	@Path("/insertcustomer")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start insertCustomer");
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
	          	  	Customer customer = gson.fromJson(jsonString, Customer.class);
	                
	                em.getTransaction().begin();
	                em.persist(customer);
	                em.getTransaction().commit();
	                
	                response = "Customer created";

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End insertCustomer");
        }
        return response;	
	}

	//http://localhost:8080/TravelExperts2/rs/db/updatecustomer
	@POST
	@Path("/updatecustomer")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start updateCustomer");
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
	          	  	Customer newCustomer = gson.fromJson(jsonString, Customer.class);
	          	  	
	          	  	Customer oldCustomer = em.find(Customer.class, newCustomer.getCustomerId());
	          	  	
	                
	                em.getTransaction().begin();
	                oldCustomer.setCustAddress(newCustomer.getCustAddress());
	                oldCustomer.setCustBusPhone(newCustomer.getCustBusPhone());
	                oldCustomer.setCustCity(newCustomer.getCustCity());
	                oldCustomer.setCustCountry(newCustomer.getCustCountry());
	                oldCustomer.setCustEmail(newCustomer.getCustEmail());
	                oldCustomer.setCustFirstName(newCustomer.getCustFirstName());
	                oldCustomer.setCustHomePhone(newCustomer.getCustHomePhone());
	                oldCustomer.setCustHomePhone(newCustomer.getCustHomePhone());
	                oldCustomer.setCustLastName(newCustomer.getCustLastName());
	                oldCustomer.setCustPostal(newCustomer.getCustPostal());
	                oldCustomer.setCustProv(newCustomer.getCustProv());
	                em.getTransaction().commit();
	                
	                response = "Customer Updated";
	                
	               

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End updateCustomer");
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
	
	//http://localhost:8080/TravelExperts2/rs/db/getcurrentpackages	
	@GET
	@Path("/getcurrentpackages")
    @Produces(MediaType.APPLICATION_JSON)
	public String getCurrentPackages(@QueryParam("request") String request ,
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
	                
	                Query query = em.createQuery("select p from Packag p "
	                		+ "WHERE p.pkgStartDate > CURRENT_DATE");
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
	
	//http://localhost:8080/TravelExperts2/rs/db/insertpackage
	@POST
	@Path("/insertpackage")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String insertPackage(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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

	//http://localhost:8080/TravelExperts2/rs/db/updatepackage
	@POST
	@Path("/updatepackage")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String updatePackage(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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
	          	  	Packag newPackage = gson.fromJson(jsonString, Packag.class);
	          	  	
	          	  	Packag oldPackage = em.find(Packag.class, newPackage.getPackageId());
	          	  	
	                
	                em.getTransaction().begin();
	                oldPackage.setPkgAgencyCommission(newPackage.getPkgAgencyCommission());
	                oldPackage.setPkgBasePrice(newPackage.getPkgBasePrice());
	                oldPackage.setPkgDesc(newPackage.getPkgDesc());
	                oldPackage.setPkgEndDate(newPackage.getPkgEndDate());
	                oldPackage.setPkgName(newPackage.getPkgName());
	                oldPackage.setPkgStartDate(newPackage.getPkgStartDate());
	                em.getTransaction().commit();
	                
	                response = "Package Updated";
	                
	               

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

	//http://localhost:8080/TravelExperts2/rs/db/deletepackage
	@DELETE
	@Path("/deletepackage/{packageid}")
	public String deletePackage(@PathParam("packageid") int packageid, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deletePackage");
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
	                
	                Packag delPackage = em.find(Packag.class, packageid);
	                	                
	                em.getTransaction().begin();
	                em.remove(delPackage);
	                em.getTransaction().commit();
	                
	                response = "Package Deleted";
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End deletePackage");
        }
        return response;
	}
	
	/*
	 * This block of code does crud operations on packages
	 */
	
	//http://localhost:8080/TravelExperts2/rs/db/getallpackagesproductsuppliers	
	@GET
	@Path("/getallpackagesproductsuppliers")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllPackagesProductsSuppliers(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllPackagesProductsSuppliers");
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
	                
	                Query query = em.createQuery("SELECT p FROM PackagesProductsSupplier p");
	                List<PackagesProductsSupplier> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<PackagesProductsSupplier>>() {}.getType();
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
            logger.debug("End getPackagesProductsSuppliers");
        }
        return response;	
	}

	
	//http://localhost:8080/TravelExperts2/rs/db/insertpackagesproductsupplier
	@POST
	@Path("/insertpackagesproductsupplier")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String insertPackagesProductsSupplier(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

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
	                PackagesProductsSupplier packageProductSupplier = gson.fromJson(jsonString, PackagesProductsSupplier.class);
	                
	                em.getTransaction().begin();
	                em.persist(packageProductSupplier);
	                em.getTransaction().commit();
	                
	                response = "Package product supplier created";

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postpackageProductSupplier");
        }
        return response;	
	}


	//http://localhost:8080/TravelExperts2/rs/db/deletepackagesproductssupplier
	@DELETE
	@Path("/deletepackagesproductssupplier/{packagesproductssupplierid}")
	public String deletePackagesProductsSupplier(@PathParam("packagesproductssupplierid") int packagesproductssupplierid, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deletePackagesProductsSupplier");
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
	                
	                PackagesProductsSupplier delPackagesProductsSupplier = em.find(PackagesProductsSupplier.class, packagesproductssupplierid);
	                	                
	                em.getTransaction().begin();
	                em.remove(delPackagesProductsSupplier);
	                em.getTransaction().commit();
	                
	                response = "Package product supplier Deleted";
	                
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End deletePackagesProductsSupplier");
        }
        return response;
	}
	/*
	 * This block of code does crud operations on bookings
	 */
	
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
	
	/*
	 * This block of code does crud operations on agents
	 */
	
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
	
	/*
	 * This block of code does crud operations on products
	 */
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
	

	// Added postProduct() -- Corinne Mullan
	// http://localhost:8080/TravelExperts2/rs/db/insertproduct
	@POST
	@Path("/insertproduct")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.TEXT_PLAIN)
	public String postProduct(String jsonString, @FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postProduct");
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
	          	  	Product product = gson.fromJson(jsonString, Product.class);
	                
	                em.getTransaction().begin();
	                em.persist(product);
	                em.getTransaction().commit();
	                
	                response = "Product created";

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postProduct");
        }
        return response;	
	}
	
	// Added /getallsuppliers -- Corinne Mullan
	// http://localhost:8080/TravelExperts2/rs/db/getallsuppliers
	
	@GET
	@Path("/getallsuppliers")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllSuppliers(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSuppiers");
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
	                
	                Query query = em.createQuery("select s from Supplier s");
	                List<Product> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<Supplier>>() {}.getType();
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
            logger.debug("End getAllSuppliers");
        }
        return response;	
	}
	
	// Added /getallproductssuppliers -- Corinne Mullan
	// http://localhost:8080/TravelExperts2/rs/db/getallproductssuppliers
	
	@GET
	@Path("/getallproductssuppliers")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllProductsSuppliers(@QueryParam("request") String request ,
			 @DefaultValue("1") @QueryParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllProductsSuppliers");
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
	                
	                Query query = em.createQuery("select ps.productSupplierId, ps.product.productId, " + 
	                                             "ps.product.prodName, ps.supplierId, s.supName " + 
	                		                     "from ProductsSupplier ps inner join Supplier s " + 
	                                             "where s.supplierId = ps.supplierId");
	                
	                List<ProductsSuppliersReturn> list = query.getResultList();
	                
	                Gson gson = new Gson();
	                Type type = new TypeToken<List<ProductsSuppliersReturn>>() {}.getType();
	                response = gson.toJson(list, type);
	                
	                /*response = "[";
	                for (ProductsSuppliersReturn listItem : list) {
	                	response += listItem.toString();
	                	response += ",";
	                }
	                response = response.replace((char) (response.length()-1), ']');*/

	                break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAllProductsSuppliers");
        }
        return response;	
	}

}

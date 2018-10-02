package restservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class PackageProductSupplierRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public PackageProductSupplierRestServiceApplication() {
		singletons.add(new PackageProductSupplierRestService());
	}
 
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}

}

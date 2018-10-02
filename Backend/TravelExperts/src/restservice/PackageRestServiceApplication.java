package restservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class PackageRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public PackageRestServiceApplication() {
		singletons.add(new PackageRestService());
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

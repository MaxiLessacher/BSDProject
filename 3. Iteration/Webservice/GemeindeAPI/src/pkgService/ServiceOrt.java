package pkgService;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pkgClasses.Ort;
import pkgDatabase.Database;

@Path("/ServiceOrt")
@XmlRootElement (name ="ServiceOrt")
public class ServiceOrt {
	@XmlElement (name ="orte")
	private Vector<Ort> vecOrt = new Vector<Ort>();
	
	public ServiceOrt(){
		
	}
	
	public Vector<Ort> getOrt(){
		return this.vecOrt;
	}
	
	public void setVector(Vector<Ort> ov){
		this.vecOrt = ov;
	}
	
	@Path("/getOrte")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Ort> getOrte() {
		Database db = Database.getInstance();
		return db.getVecOrt();
	}
	
/*	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{bookid}")
	  public Book printXMLBook(@PathParam("bookid") String id) {
		Book retValue = null;
		
		try {

			Database db = Database.getInstance();
			
			retValue = db.getBook(Integer.parseInt(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return retValue;
	  }*/
	
	@Path("/ADD")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	  public String addOrt(Ort ort) {
		if(ort != null){
			this.vecOrt.add(ort);
			Database db = Database.getInstance();
			db.addOrt(ort);
		}
	    return "Added Ort successfully";
	  }
	
	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	  public String updateOrt(Ort oldOrt, Ort newOrt) {
		String retValue = "";
		if(oldOrt != null && newOrt!=null){
			Database db = Database.getInstance();
			try {
				db.updateOrt(oldOrt, newOrt);
				retValue="updated " + oldOrt.toString() + " to " + newOrt.toString();
			}
			catch (Exception e) {
				retValue = "couldn't update " + oldOrt.toString();
			}
		}
	    return retValue;
	  }
	
	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	  public String deleteOrt(Ort ort) {
		if(ort != null){
			boolean found = false;
			for(int i = 0;i<this.vecOrt.size()&&!found;i++){
				if(this.vecOrt.get(i).compareTo(ort) == 0){
					this.vecOrt.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteOrt(ort);
		}
	    return "Deleted Ort successfully";
	  }
	
	
}

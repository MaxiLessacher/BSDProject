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

import pkgClasses.Haushalt;
import pkgDatabase.Database;

@Path("/ServiceHaushalt")
@XmlRootElement(name = "ServiceHaushalt")
public class ServiceHaushalt {
	@XmlElement(name = "Haushaltn")
	private Vector<Haushalt> vecHaushalt = new Vector<Haushalt>();

	public ServiceHaushalt() {

	}

	public Vector<Haushalt> getVectorHaushalt() {
		return this.vecHaushalt;
	}

	public void setVectorHaushalt(Vector<Haushalt> ov) {
		this.vecHaushalt = ov;
	}

	@Path("/getHaushalte")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Haushalt> getHaushalte() {
		Database db = Database.getInstance();
		return db.getVecHaushalt();
	}

	/*
	 * @GET
	 * 
	 * @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	 * 
	 * @Path("{bookid}") public Book printXMLBook(@PathParam("bookid") String
	 * id) { Book retValue = null;
	 * 
	 * try {
	 * 
	 * Database db = Database.getInstance();
	 * 
	 * retValue = db.getBook(Integer.parseInt(id));
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return retValue; }
	 */

	@Path("/ADD")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String addHaushalt(Haushalt Haushalt) {
		if (Haushalt != null) {
			this.vecHaushalt.add(Haushalt);
			Database db = Database.getInstance();
			db.addHaushalt(Haushalt);
		}
		return "Added Haushalt successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateHaushalt(Haushalt oldHaushalt, Haushalt newHaushalt) {
		String retValue = "";
		if (oldHaushalt != null && newHaushalt != null) {
			Database db = Database.getInstance();
			try {
				db.updateHaushalt(oldHaushalt, newHaushalt);
				retValue = "updated " + oldHaushalt.toString() + " to "
						+ newHaushalt.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldHaushalt.toString();
			}
		}
		return retValue;
	}

	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteHaushalt(Haushalt Haushalt) {
		if (Haushalt != null) {
			boolean found = false;
			for (int i = 0; i < this.vecHaushalt.size() && !found; i++) {
				if (this.vecHaushalt.get(i).compareTo(Haushalt) == 0) {
					this.vecHaushalt.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteHaushalt(Haushalt);
		}
		return "Deleted Haushalt successfully";
	}

}

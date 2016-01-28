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

import pkgClasses.Verwaltungspersonal;
import pkgDatabase.Database;

@Path("/ServiceVerwaltungspersonal")
@XmlRootElement(name = "ServiceVerwaltungspersonal")
public class ServiceVerwaltungspersonal {
	@XmlElement(name = "Verwaltungspersonaln")
	private Vector<Verwaltungspersonal> vecVerwaltungspersonal = new Vector<Verwaltungspersonal>();

	public ServiceVerwaltungspersonal() {

	}

	public Vector<Verwaltungspersonal> getVectorVerwaltungspersonal() {
		return this.vecVerwaltungspersonal;
	}

	public void setVectorVerwaltungspersonal(Vector<Verwaltungspersonal> ov) {
		this.vecVerwaltungspersonal = ov;
	}

	@Path("/getVerwaltungspersonal")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Verwaltungspersonal> getVerwaltungspersonal() {
		Database db = Database.getInstance();
		return db.getVecVerwaltungspersonal();
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
	public String addVerwaltungspersonal(Verwaltungspersonal Verwaltungspersonal) {
		if (Verwaltungspersonal != null) {
			this.vecVerwaltungspersonal.add(Verwaltungspersonal);
			Database db = Database.getInstance();
			db.addVerwaltungspersonal(Verwaltungspersonal);
		}
		return "Added Verwaltungspersonal successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateVerwaltungspersonal(Verwaltungspersonal oldVerwaltungspersonal, Verwaltungspersonal newVerwaltungspersonal) {
		String retValue = "";
		if (oldVerwaltungspersonal != null && newVerwaltungspersonal != null) {
			Database db = Database.getInstance();
			try {
				db.updateVerwaltungspersonal(oldVerwaltungspersonal, newVerwaltungspersonal);
				retValue = "updated " + oldVerwaltungspersonal.toString() + " to "
						+ newVerwaltungspersonal.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldVerwaltungspersonal.toString();
			}
		}
		return retValue;
	}

	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteVerwaltungspersonal(Verwaltungspersonal Verwaltungspersonal) {
		if (Verwaltungspersonal != null) {
			boolean found = false;
			for (int i = 0; i < this.vecVerwaltungspersonal.size() && !found; i++) {
				if (this.vecVerwaltungspersonal.get(i).compareTo(Verwaltungspersonal) == 0) {
					this.vecVerwaltungspersonal.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteVerwaltungspersonal(Verwaltungspersonal);
		}
		return "Deleted Verwaltungspersonal successfully";
	}

}

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

import pkgClasses.Mitglied;
import pkgDatabase.Database;

@Path("/ServiceMitglieder")
@XmlRootElement(name = "ServiceMitglieder")
public class ServiceMitglieder {
	@XmlElement(name = "Mitgliedn")
	private Vector<Mitglied> vecMitglied = new Vector<Mitglied>();

	public ServiceMitglieder() {

	}

	public Vector<Mitglied> getVectorMitglied() {
		return this.vecMitglied;
	}

	public void setVectorMitglied(Vector<Mitglied> ov) {
		this.vecMitglied = ov;
	}

	@Path("/getMitglieder")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Mitglied> getMitglieder() {
		Database db = Database.getInstance();
		return db.getVecMitglied();
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
	public String addMitglied(Mitglied Mitglied) {
		if (Mitglied != null) {
			this.vecMitglied.add(Mitglied);
			Database db = Database.getInstance();
			db.addMitglied(Mitglied);
		}
		return "Added Mitglied successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateMitglied(Mitglied oldMitglied, Mitglied newMitglied) {
		String retValue = "";
		if (oldMitglied != null && newMitglied != null) {
			Database db = Database.getInstance();
			try {
				db.updateMitglied(oldMitglied, newMitglied);
				retValue = "updated " + oldMitglied.toString() + " to "
						+ newMitglied.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldMitglied.toString();
			}
		}
		return retValue;
	}

	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteMitglied(Mitglied Mitglied) {
		if (Mitglied != null) {
			boolean found = false;
			for (int i = 0; i < this.vecMitglied.size() && !found; i++) {
				if (this.vecMitglied.get(i).compareTo(Mitglied) == 0) {
					this.vecMitglied.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteMitglied(Mitglied);
		}
		return "Deleted Mitglied successfully";
	}

}

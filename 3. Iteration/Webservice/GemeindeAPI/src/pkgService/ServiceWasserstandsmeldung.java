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

import pkgClasses.Wasserstandsmeldung;
import pkgDatabase.Database;

@Path("/ServiceWasserstandsmeldung")
@XmlRootElement(name = "ServiceWasserstandsmeldung")
public class ServiceWasserstandsmeldung {
	@XmlElement(name = "Wasserstandsmeldungn")
	private Vector<Wasserstandsmeldung> vecWasserstandsmeldung = new Vector<Wasserstandsmeldung>();

	public ServiceWasserstandsmeldung() {

	}

	public Vector<Wasserstandsmeldung> getVectorWasserstandsmeldung() {
		return this.vecWasserstandsmeldung;
	}

	public void setVectorWasserstandsmeldung(Vector<Wasserstandsmeldung> ov) {
		this.vecWasserstandsmeldung = ov;
	}

	@Path("/getWasserstandsmeldungen")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Wasserstandsmeldung> getWasserstandsmeldungen() {
		Database db = Database.getInstance();
		return db.getVecWasserstandsmeldung();
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

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String addWasserstandsmeldung(Wasserstandsmeldung Wasserstandsmeldung) {
		if (Wasserstandsmeldung != null) {
			this.vecWasserstandsmeldung.add(Wasserstandsmeldung);
			Database db = Database.getInstance();
			db.addWasserstandsmeldung(Wasserstandsmeldung);
		}
		return "Added Wasserstandsmeldung successfully";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateWasserstandsmeldung(Wasserstandsmeldung oldWasserstandsmeldung, Wasserstandsmeldung newWasserstandsmeldung) {
		String retValue = "";
		if (oldWasserstandsmeldung != null && newWasserstandsmeldung != null) {
			Database db = Database.getInstance();
			try {
				db.updateWasserstandsmeldung(oldWasserstandsmeldung, newWasserstandsmeldung);
				retValue = "updated " + oldWasserstandsmeldung.toString() + " to "
						+ newWasserstandsmeldung.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldWasserstandsmeldung.toString();
			}
		}
		return retValue;
	}

	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteWasserstandsmeldung(Wasserstandsmeldung Wasserstandsmeldung) {
		if (Wasserstandsmeldung != null) {
			boolean found = false;
			for (int i = 0; i < this.vecWasserstandsmeldung.size() && !found; i++) {
				if (this.vecWasserstandsmeldung.get(i).compareTo(Wasserstandsmeldung) == 0) {
					this.vecWasserstandsmeldung.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteWasserstandsmeldung(Wasserstandsmeldung);
		}
		return "Deleted Wasserstandsmeldung successfully";
	}

}

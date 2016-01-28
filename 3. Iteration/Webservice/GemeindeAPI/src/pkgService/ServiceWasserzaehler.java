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

import pkgClasses.Wasserzaehler;
import pkgDatabase.Database;

@Path("/ServiceWasserzaehler")
@XmlRootElement(name = "ServiceWasserzaehler")
public class ServiceWasserzaehler {
	@XmlElement(name = "Wasserzaehlern")
	private Vector<Wasserzaehler> vecWasserzaehler = new Vector<Wasserzaehler>();

	public ServiceWasserzaehler() {

	}

	public Vector<Wasserzaehler> getVectorWasserzaehler() {
		return this.vecWasserzaehler;
	}

	public void setVectorWasserzaehler(Vector<Wasserzaehler> ov) {
		this.vecWasserzaehler = ov;
	}

	@Path("/getWasserzaehler")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Wasserzaehler> getWasserzaehler() {
		Database db = Database.getInstance();
		return db.getVecWasserzaehler();
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
	public String addWasserzaehler(Wasserzaehler Wasserzaehler) {
		if (Wasserzaehler != null) {
			this.vecWasserzaehler.add(Wasserzaehler);
			Database db = Database.getInstance();
			db.addWasserzaehler(Wasserzaehler);
		}
		return "Added Wasserzaehler successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateWasserzaehler(Wasserzaehler oldWasserzaehler, Wasserzaehler newWasserzaehler) {
		String retValue = "";
		if (oldWasserzaehler != null && newWasserzaehler != null) {
			Database db = Database.getInstance();
			try {
				db.updateWasserzaehler(oldWasserzaehler, newWasserzaehler);
				retValue = "updated " + oldWasserzaehler.toString() + " to "
						+ newWasserzaehler.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldWasserzaehler.toString();
			}
		}
		return retValue;
	}

	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteWasserzaehler(Wasserzaehler Wasserzaehler) {
		if (Wasserzaehler != null) {
			boolean found = false;
			for (int i = 0; i < this.vecWasserzaehler.size() && !found; i++) {
				if (this.vecWasserzaehler.get(i).compareTo(Wasserzaehler) == 0) {
					this.vecWasserzaehler.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteWasserzaehler(Wasserzaehler);
		}
		return "Deleted Wasserzaehler successfully";
	}

}

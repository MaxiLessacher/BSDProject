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

import pkgClasses.Adresse;
import pkgDatabase.Database;

@Path("/ServiceAdresse")
@XmlRootElement(name = "ServiceAdresse")
public class ServiceAdresse {
	@XmlElement(name = "Adressen")
	private Vector<Adresse> vecAdresse = new Vector<Adresse>();

	public ServiceAdresse() {

	}

	public Vector<Adresse> getVectorAdresse() {
		return this.vecAdresse;
	}

	public void setVectorAdresse(Vector<Adresse> ov) {
		this.vecAdresse = ov;
	}

	@Path("/getAdressen")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Adresse> getAdressen() {
		Database db = Database.getInstance();
		return db.getVecAdresse();
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
	public String addAdresse(Adresse Adresse) {
		if (Adresse != null) {
			this.vecAdresse.add(Adresse);
			Database db = Database.getInstance();
			db.addAdresse(Adresse);
		}
		return "Added Adresse successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateAdresse(Adresse oldAdresse, Adresse newAdresse) {
		String retValue = "";
		if (oldAdresse != null && newAdresse != null) {
			Database db = Database.getInstance();
			try {
				db.updateAdresse(oldAdresse, newAdresse);
				retValue = "updated " + oldAdresse.toString() + " to "
						+ newAdresse.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldAdresse.toString();
			}
		}
		return retValue;
	}

	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteAdresse(Adresse Adresse) {
		if (Adresse != null) {
			boolean found = false;
			for (int i = 0; i < this.vecAdresse.size() && !found; i++) {
				if (this.vecAdresse.get(i).compareTo(Adresse) == 0) {
					this.vecAdresse.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteAdresse(Adresse);
		}
		return "Deleted Adresse successfully";
	}

}

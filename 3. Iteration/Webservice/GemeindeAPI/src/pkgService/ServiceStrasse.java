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

import pkgClasses.Strasse;
import pkgDatabase.Database;

@Path("/ServiceStrasse")
@XmlRootElement(name = "ServiceStrasse")
public class ServiceStrasse {
	@XmlElement(name = "Strassen")
	private Vector<Strasse> vecStrasse = new Vector<Strasse>();

	public ServiceStrasse() {

	}

	public Vector<Strasse> getVectorStrasse() {
		return this.vecStrasse;
	}

	public void setVectorStrasse(Vector<Strasse> ov) {
		this.vecStrasse = ov;
	}

	@Path("/getStrassen")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Vector<Strasse> getStrassen() {
		Database db = Database.getInstance();
		return db.getVecStrasse();
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
	public String addStrasse(Strasse Strasse) {
		if (Strasse != null) {
			this.vecStrasse.add(Strasse);
			Database db = Database.getInstance();
			db.addStrasse(Strasse);
		}
		return "Added Strasse successfully";
	}

	@Path("/UPDATE")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public String updateStrasse(Strasse oldStrasse, Strasse newStrasse) {
		String retValue = "";
		if (oldStrasse != null && newStrasse != null) {
			Database db = Database.getInstance();
			try {
				db.updateStrasse(oldStrasse, newStrasse);
				retValue = "updated " + oldStrasse.toString() + " to "
						+ newStrasse.toString();
			} catch (Exception e) {
				retValue = "couldn't update " + oldStrasse.toString();
			}
		}
		return retValue;
	}
	
	@Path("/DELETE")
	@DELETE
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public String deleteStrasse(Strasse Strasse) {
		if (Strasse != null) {
			boolean found = false;
			for (int i = 0; i < this.vecStrasse.size() && !found; i++) {
				if (this.vecStrasse.get(i).compareTo(Strasse) == 0) {
					this.vecStrasse.remove(i);
				}
			}
			Database db = Database.getInstance();
			db.deleteStrasse(Strasse);
		}
		return "Deleted Strasse successfully";
	}

}

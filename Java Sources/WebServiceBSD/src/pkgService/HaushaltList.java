package pkgService;

import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/HaushaltList")
public class HaushaltList {
	public HaushaltList() {
		super();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Vector<Haushalt> getHaushalte() {
		Database db = Database.newInstance();
		return db.getHaushalte();
	}
}

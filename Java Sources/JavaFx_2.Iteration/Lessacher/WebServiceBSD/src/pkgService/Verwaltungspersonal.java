package pkgService;

public class Verwaltungspersonal {
	private int personal_id;
	private String name;
	private String abteilung;

	public Verwaltungspersonal() {

	}

	public Verwaltungspersonal(int personal_id, String name, String abteilung) {
		this.personal_id = personal_id;
		this.name = name;
		this.abteilung = abteilung;
	}

	public int getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(int personal_id) {
		this.personal_id = personal_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}


}

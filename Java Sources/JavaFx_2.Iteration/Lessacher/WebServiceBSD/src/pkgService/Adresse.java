package pkgService;

public class Adresse {
	private int plz;
	private String strasse;
	private int hausnummer;

	public Adresse() {
		super();
	}

	public Adresse(int plz, String strasse, int hausnummer) {
		super();
		this.plz = plz;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}


}

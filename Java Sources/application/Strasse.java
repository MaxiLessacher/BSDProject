package application;

public class Strasse {
	private String strasse;
	private int plz;

	public Strasse() {
		super();
	}

	public Strasse(String strasse, int plz) {
		super();
		this.strasse = strasse;
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

}

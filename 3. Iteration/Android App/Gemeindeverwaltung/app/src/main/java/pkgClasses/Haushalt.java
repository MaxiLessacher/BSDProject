package pkgClasses;

import java.io.Serializable;

public class Haushalt implements Comparable<Haushalt>, Serializable {
	private int HH_ID;
	private String strasse;
	private int plz;
	private int hausnummer;
	private int tuernummer;
	private int wohnflaeche;
	private boolean isLandwirtschaft;
	private boolean isGarten;

	public Haushalt() {
		super();
	}

	public Haushalt(int hH_ID, String strasse, int plz, int hausnummer, int tuernummer, int wohnflaeche,
			boolean isLandwirtschaft, boolean isGarten) {
		super();
		HH_ID = hH_ID;
		this.strasse = strasse;
		this.plz = plz;
		this.hausnummer = hausnummer;
		this.tuernummer = tuernummer;
		this.wohnflaeche = wohnflaeche;
		this.isLandwirtschaft = isLandwirtschaft;
		this.isGarten = isGarten;
	}

	public int getHH_ID() {
		return HH_ID;
	}

	public void setHH_ID(int hH_ID) {
		HH_ID = hH_ID;
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

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

	public int getTuernummer() {
		return tuernummer;
	}

	public void setTuernummer(int tuernummer) {
		this.tuernummer = tuernummer;
	}

	public int getWohnflaeche() {
		return wohnflaeche;
	}

	public void setWohnflaeche(int wohnflaeche) {
		this.wohnflaeche = wohnflaeche;
	}

	public boolean isLandwirtschaft() {
		return isLandwirtschaft;
	}

	public void setLandwirtschaft(boolean isLandwirtschaft) {
		this.isLandwirtschaft = isLandwirtschaft;
	}

	public boolean isGarten() {
		return isGarten;
	}

	public void setGarten(boolean isGarten) {
		this.isGarten = isGarten;
	}

	@Override
	public int compareTo(Haushalt o) {
		int retValue = 1;
		if (HH_ID == o.getHH_ID()) {
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Haushalt [HH_ID=" + HH_ID + ", strasse=" + strasse + ", plz="
				+ plz + ", hausnummer=" + hausnummer + ", tuernummer="
				+ tuernummer + ", wohnflaeche=" + wohnflaeche
				+ ", isLandwirtschaft=" + isLandwirtschaft + ", isGarten="
				+ isGarten + "]";
	}
}

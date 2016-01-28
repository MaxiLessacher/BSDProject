package pkgClasses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse implements Comparable<Adresse> {
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

	@Override
	public int compareTo(Adresse a) {
		int retValue = 1;
		if (plz == a.getPlz()) {
			if (strasse.equals(a.getStrasse())) {
				if (hausnummer==a.getHausnummer()) {
					retValue = 0;
				}
			}
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Adresse [plz=" + plz + ", strasse=" + strasse + ", hausnummer="
				+ hausnummer + "]";
	}
}

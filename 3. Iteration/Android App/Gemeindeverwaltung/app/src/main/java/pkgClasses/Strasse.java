package pkgClasses;

import java.io.Serializable;

public class Strasse implements Comparable<Strasse>, Serializable {
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

	@Override
	public int compareTo(Strasse o) {
		int retValue = 1;
		if (plz == o.getPlz()) {
			retValue = strasse.compareTo(o.getStrasse());
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Strasse [strasse=" + strasse + ", plz=" + plz + "]";
	}
}

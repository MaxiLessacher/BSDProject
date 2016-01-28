package pkgClasses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ort implements Comparable<Ort>{
	private int plz;
	private String ort;

	public Ort() {

	}

	public Ort(int _plz, String _ort) {
		this.plz = _plz;
		this.ort = _ort;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Override
	public int compareTo(Ort o) {
		int retValue = 1;
		if (plz == o.getPlz()) {
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Ort [plz=" + plz + ", ort=" + ort + "]";
	}
}

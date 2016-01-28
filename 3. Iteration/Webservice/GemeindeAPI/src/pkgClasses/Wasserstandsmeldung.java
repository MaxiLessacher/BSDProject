package pkgClasses;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wasserstandsmeldung implements Comparable<Wasserstandsmeldung>{
	private Date datum;
	private int zaehlerNr;
	private int neuZaehlerstand;

	public Wasserstandsmeldung() {
	}

	public Wasserstandsmeldung(Date datum, int zaehlerNr, int neuZaehlerstand) {
		this.datum = datum;
		this.zaehlerNr = zaehlerNr;
		this.neuZaehlerstand = neuZaehlerstand;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getZaehlerNr() {
		return zaehlerNr;
	}

	public void setZaehlerNr(int zaehlerNr) {
		this.zaehlerNr = zaehlerNr;
	}

	public int getNeuZaehlerstand() {
		return neuZaehlerstand;
	}

	public void setNeuZaehlerstand(int neuZaehlerstand) {
		this.neuZaehlerstand = neuZaehlerstand;
	}

	@Override
	public String toString() {
		return "Wasserstandsmeldung [date=" + datum + ", zaehler_nr="
				+ zaehlerNr + ", neuZaehlerstand=" + neuZaehlerstand + "]";
	}

	@Override
	public int compareTo(Wasserstandsmeldung o) {
		int retValue = 1;
		if (zaehlerNr == o.getZaehlerNr()) {
			retValue = datum.compareTo(o.getDatum());
		}
		return retValue;
	}

}

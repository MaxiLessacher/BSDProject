package pkgService;

import java.util.Date;


public class Wasserstandsmeldung {
	private Date date;
	private int zaehler_nr;
	private int neuZaehlerstand;

	public Wasserstandsmeldung() {

	}

	public Wasserstandsmeldung(Date _date, int zaehler_nr, int neuZaehlerstand) {
		this.date = _date;
		this.zaehler_nr = zaehler_nr;
		this.neuZaehlerstand = neuZaehlerstand;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getZaehler_nr() {
		return zaehler_nr;
	}

	public void setZaehler_nr(int zaehler_nr) {
		this.zaehler_nr = zaehler_nr;
	}

	public int getNeuZaehlerstand() {
		return neuZaehlerstand;
	}

	public void setNeuZaehlerstand(int neuZaehlerstand) {
		this.neuZaehlerstand = neuZaehlerstand;
	}

}

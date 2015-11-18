package application;

import java.time.LocalDate;

public class Wasserstandsmeldung {
	private LocalDate date;
	private int zaehler_nr;
	private int neuZaehlerstand;

	public Wasserstandsmeldung() {

	}

	public Wasserstandsmeldung(LocalDate _date, int zaehler_nr, int neuZaehlerstand) {
		this.date = _date;
		this.zaehler_nr = zaehler_nr;
		this.neuZaehlerstand = neuZaehlerstand;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

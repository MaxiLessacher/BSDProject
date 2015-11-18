package application;

import java.sql.Connection;
import java.util.TreeSet;

public class Database {
	private TreeSet<Ort> tsOrt = new TreeSet<Ort>();
	private TreeSet<Strasse> tsStrasse = new TreeSet<Strasse>();
	private TreeSet<Adresse> tsAdresse = new TreeSet<Adresse>();
	private TreeSet<Haushalt> tsHaushalt = new TreeSet<Haushalt>();
	private TreeSet<Mitglied> tsMitglied = new TreeSet<Mitglied>();
	private TreeSet<Wasserzaehler> tsWasserzaehler = new TreeSet<Wasserzaehler>();
	private TreeSet<Wasserstandsmeldung> tsWasserstandsmeldung = new TreeSet<Wasserstandsmeldung>();
	private TreeSet<Verwaltungspersonal> tsVerwaltungspersonal = new TreeSet<Verwaltungspersonal>();
	Connection conn;

	public Database() {

	}

	public TreeSet<Ort> getTsOrt() {
		return tsOrt;
	}

	public void setTsOrt(TreeSet<Ort> tsOrt) {
		this.tsOrt = tsOrt;
	}

	public TreeSet<Strasse> getTsStrasse() {
		return tsStrasse;
	}

	public void setTsStrasse(TreeSet<Strasse> tsStrasse) {
		this.tsStrasse = tsStrasse;
	}

	public TreeSet<Adresse> getTsAdresse() {
		return tsAdresse;
	}

	public void setTsAdresse(TreeSet<Adresse> tsAdresse) {
		this.tsAdresse = tsAdresse;
	}

	public TreeSet<Haushalt> getTsHaushalt() {
		return tsHaushalt;
	}

	public void setTsHaushalt(TreeSet<Haushalt> tsHaushalt) {
		this.tsHaushalt = tsHaushalt;
	}

	public TreeSet<Mitglied> getTsMitglied() {
		return tsMitglied;
	}

	public void setTsMitglied(TreeSet<Mitglied> tsMitglied) {
		this.tsMitglied = tsMitglied;
	}

	public TreeSet<Wasserzaehler> getTsWasserzaehler() {
		return tsWasserzaehler;
	}

	public void setTsWasserzaehler(TreeSet<Wasserzaehler> tsWasserzaehler) {
		this.tsWasserzaehler = tsWasserzaehler;
	}

	public TreeSet<Wasserstandsmeldung> getTsWasserstandsmeldung() {
		return tsWasserstandsmeldung;
	}

	public void setTsWasserstandsmeldung(TreeSet<Wasserstandsmeldung> tsWasserstandsmeldung) {
		this.tsWasserstandsmeldung = tsWasserstandsmeldung;
	}

	public TreeSet<Verwaltungspersonal> getTsVerwaltungspersonal() {
		return tsVerwaltungspersonal;
	}

	public void setTsVerwaltungspersonal(TreeSet<Verwaltungspersonal> tsVerwaltungspersonal) {
		this.tsVerwaltungspersonal = tsVerwaltungspersonal;
	}


}

package pkgDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import application.Adresse;
import application.Ort;
import application.Strasse;
import application.Haushalt;
import application.Mitglied;
import application.Verwaltungspersonal;
import application.Wasserzaehler;
import application.Wasserstandsmeldung;


public class Database {
	private Connection conn;
	private Vector<Ort> vecOrt = new Vector<Ort>();
	private Vector<Strasse> vecStrasse = new Vector<Strasse>();
	private Vector<Adresse> vecAdresse = new Vector<Adresse>();
	private Vector<Haushalt> vecHaushalt = new Vector<Haushalt>();
	private Vector<Mitglied> vecMitglied = new Vector<Mitglied>();
	private Vector<Verwaltungspersonal> vecVerwaltungspersonal = new Vector<Verwaltungspersonal>();
	private Vector<Wasserzaehler> vecWasserzaehler = new Vector<Wasserzaehler>();
	private Vector<Wasserstandsmeldung> vecWasserstandsmeldung = new Vector<Wasserstandsmeldung>();


	public Database() {
		try {
			this.conn = createConnection("jdbc:oracle:thin:@192.168.128.151:1521:ora11g","d5bhifs06", "d5bhifs06");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection createConnection(String connStrg, String user,
			String pwd) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(connStrg, user, pwd);
	}

	public void loadOrtFromDb() throws SQLException {
		String select = "select * from ort";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addOrt(new Ort(rs.getInt("plz"), rs.getString("ort")));
		}
	}

	public void loadStrasseFromDb() throws SQLException {
		String select = "select * from strassen";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addStrasse(new Strasse(rs.getString("strasse"), rs.getInt("plz")));
		}
	}

	public void loadAdressenFromDb() throws SQLException {
		String select = "select * from adressen";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addAdresse(new Adresse(rs.getInt("plz"), rs.getString("strasse"), rs.getInt("hausnummer")));
		}
	}

	public void loadHaushalteFromDb() throws SQLException {
		String select = "select * from haushalt";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addHaushalt(new Haushalt(rs.getInt("hh_id"), rs.getString("strasse"), rs.getInt("plz"), rs.getInt("hausnummer"),
					rs.getInt("tuernummer"), rs.getInt("wohnflaeche"), rs.getBoolean("landwirtschaft"), rs.getBoolean("garten")));
		}
	}

	public void loadMitgliederFromDb() throws SQLException {
		String select = "select * from mitglieder";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addMitglied(new Mitglied(rs.getInt("mitglieds_id"), rs.getString("name"), rs.getBoolean("HH_Vorstand"),
					rs.getInt("hh_id")));
		}
	}

	public void loadVerwaltungspersonalFromDb() throws SQLException {
		String select = "select * from verwaltungspersonal";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addVerwaltungspersonal(new Verwaltungspersonal(rs.getInt("personal_id"), rs.getString("name"), rs.getString("abteilung")));
		}
	}

	public void loadWasserzaehlerFromDb() throws SQLException {
		String select = "select * from wasserzaehler";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addWasserzaehler(new Wasserzaehler(rs.getInt("zaehler_nr"), rs.getInt("hh_id"), rs.getInt("zaehlerstand"),
					rs.getBoolean("Hauptzaehler"), rs.getInt("standort_x"), rs.getInt("standort_y")));
		}
	}

	public void loadWasserstandsmeldungFromDb() throws SQLException {
		String select = "select * from wasserstandsmeldung";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			LocalDate ldate = rs.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.addWasserstandsmeldung(new Wasserstandsmeldung(ldate, rs.getInt("zaehler_nr"), rs.getInt("neuZaehlerstand")));
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Vector<Ort> getVecOrt() {
		return vecOrt;
	}

	public void setVecOrt(Vector<Ort> vecOrt) {
		this.vecOrt = vecOrt;
	}

	public Vector<Strasse> getVecStrasse() {
		return vecStrasse;
	}

	public void setVecStrasse(Vector<Strasse> vecStrasse) {
		this.vecStrasse = vecStrasse;
	}

	public Vector<Adresse> getVecAdresse() {
		return vecAdresse;
	}

	public void setVecAdresse(Vector<Adresse> vecAdresse) {
		this.vecAdresse = vecAdresse;
	}

	public Vector<Haushalt> getVecHaushalt() {
		return vecHaushalt;
	}

	public void setVecHaushalt(Vector<Haushalt> vecHaushalt) {
		this.vecHaushalt = vecHaushalt;
	}

	public Vector<Mitglied> getVecMitglied() {
		return vecMitglied;
	}

	public void setVecMitglied(Vector<Mitglied> vecMitglied) {
		this.vecMitglied = vecMitglied;
	}

	public Vector<Verwaltungspersonal> getVecVerwaltungspersonal() {
		return vecVerwaltungspersonal;
	}

	public void setVecVerwaltungspersonal(Vector<Verwaltungspersonal> vecVerwaltungspersonal) {
		this.vecVerwaltungspersonal = vecVerwaltungspersonal;
	}

	public Vector<Wasserzaehler> getVecWasserzaehler() {
		return vecWasserzaehler;
	}

	public void setVecWasserzaehler(Vector<Wasserzaehler> vecWasserzaehler) {
		this.vecWasserzaehler = vecWasserzaehler;
	}

	public Vector<Wasserstandsmeldung> getVecWasserstandsmeldung() {
		return vecWasserstandsmeldung;
	}

	public void setVecWasserstandsmeldung(Vector<Wasserstandsmeldung> vecWasserstandsmeldung) {
		this.vecWasserstandsmeldung = vecWasserstandsmeldung;
	}

	public void addOrt(Ort o) {
		this.vecOrt.add(o);
	}
	public void addStrasse(Strasse s) {
		this.vecStrasse.add(s);
	}
	public void addAdresse(Adresse a) {
		this.vecAdresse.add(a);
	}
	public void addHaushalt(Haushalt h) {
		this.vecHaushalt.add(h);
	}
	public void addMitglied(Mitglied m) {
		this.vecMitglied.add(m);
	}
	public void addVerwaltungspersonal(Verwaltungspersonal v) {
		this.vecVerwaltungspersonal.add(v);
	}
	public void addWasserzaehler(Wasserzaehler w) {
		this.vecWasserzaehler.add(w);
	}
	public void addWasserstandsmeldung(Wasserstandsmeldung w) {
		this.vecWasserstandsmeldung.add(w);
	}

	public void removeAdresse(Adresse rem) {
		for (Adresse adress : vecAdresse) {
			if (adress.getPlz()==rem.getPlz()) && adress.getStrasse().compareTo(rem.getStrasse())== 0 && adress.getHausnummer()==rem.getHausnummer())) {

			}
		}
	}

}
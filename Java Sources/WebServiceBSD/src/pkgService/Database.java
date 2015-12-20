package pkgService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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

	private String connStrgSchool = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	//private String connStrgHome = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	private String user="d5b30";
	private String pwd="d5b30";
	
	private static Database instance = null;

	public Database() {
		try {
			createConnection();
			loadHaushalteFromDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillHaushalt(){
		
	}
	
	public static Database newInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Vector<Haushalt> getHaushalte() {
		return vecHaushalt;
	}
	
	public void createConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		this.conn = DriverManager.getConnection(connStrgSchool, user, pwd);
	}

	public void loadOrtFromDb() throws SQLException {
		createConnection();
		String select = "select * from ort";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addOrt(new Ort(rs.getInt("plz"), rs.getString("name")));
		}
		conn.close();
	}

	public void loadStrasseFromDb() throws SQLException {
		createConnection();
		String select = "select * from strasse";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addStrasse(new Strasse(rs.getString("name"), rs.getInt("plz")));
		}
		conn.close();
	}

	public void loadAdressenFromDb() throws SQLException {
		createConnection();
		String select = "select * from adresse";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addAdresse(new Adresse(rs.getInt("plz"), rs.getString("name"), rs.getInt("nummer")));
		}
		conn.close();
	}

	public void loadHaushalteFromDb() throws SQLException {
		createConnection();
		String select = "select * from haushalt";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addHaushalt(new Haushalt(rs.getInt("hh_id"), rs.getString("name"), rs.getInt("plz"), rs.getInt("nummer"),
					rs.getInt("tuernr"), rs.getInt("wohnflaeche"), rs.getBoolean("landwirtschaft"), rs.getBoolean("garten")));
		}
		conn.close();
	}

	public void loadMitgliederFromDb() throws SQLException {
		createConnection();
		String select = "select * from mitglieder";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addMitglied(new Mitglied(rs.getInt("mitglieds_id"), rs.getString("name"), rs.getBoolean("hh_vorstand"),
					rs.getInt("hh_id")));
		}
		conn.close();
	}

	public void loadVerwaltungspersonalFromDb() throws SQLException {
		createConnection();
		String select = "select * from verwaltungspersonal";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addVerwaltungspersonal(new Verwaltungspersonal(rs.getInt("personalid"), rs.getString("name"), rs.getString("abteilung")));
		}
	}

	public void loadWasserzaehlerFromDb() throws SQLException {
		createConnection();
		//select w.zaehler_nr, t.x, t.y FROM wasserzaehler w  TABLE(SDO_UTIL.GETVERTICES(w.standort)) t
		String select = "select zaehler_nr, hh_id, zaehlerstand, hauptzaehler, t.x, t.y from wasserzaehler, TABLE(SDO_UTIL.GETVERTICES(standort)) t";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addWasserzaehler(new Wasserzaehler(rs.getInt("zaehler_nr"), rs.getInt("hh_id"), rs.getInt("zaehlerstand"),
					rs.getBoolean("hauptzaehler"), rs.getInt("t.x"), rs.getInt("t.y")));
		}
		conn.close();
	}

	public void loadWasserstandsmeldungFromDb() throws SQLException {
		createConnection();
		String select = "select * from wasserstandsmeldung";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			Date ldate = ( rs.getDate("date"));
			this.addWasserstandsmeldung(new Wasserstandsmeldung(ldate, rs.getInt("zaehler_nr"), rs.getInt("neuZaehlerstand")));
		}
		conn.close();
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

	public void addOrt(Ort ort) {
		this.vecOrt.add(ort);
	}
	public void addStrasse(Strasse strasse) {
		this.vecStrasse.add(strasse);
	}
	public void addAdresse(Adresse adresse) {
		this.vecAdresse.add(adresse);
	}
	public void addHaushalt(Haushalt haushalt) {
		this.vecHaushalt.add(haushalt);
	}
	public void addMitglied(Mitglied mitglied) {
		this.vecMitglied.add(mitglied);
	}
	public void addVerwaltungspersonal(Verwaltungspersonal verwaltungspersonal) {
		this.vecVerwaltungspersonal.add(verwaltungspersonal);
	}
	public void addWasserzaehler(Wasserzaehler wasserzaehler) {
		this.vecWasserzaehler.add(wasserzaehler);
	}
	public void addWasserstandsmeldung(Wasserstandsmeldung wasserstandsmeldung) {
		this.vecWasserstandsmeldung.add(wasserstandsmeldung);
	}

	public void removeAdresse(Adresse rem) throws Exception {
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		int i = 0;
		while (itrAdresse.hasNext()) {
			Adresse a = itrAdresse.next();
			if (a.getPlz()==rem.getPlz() && (a.getStrasse().compareTo(rem.getStrasse())== 0) && (a.getHausnummer()==rem.getHausnummer())) {
				vecAdresse.remove(i);
			}
			i++;
		}
	}

	public void removeOrt(Ort rem) throws Exception {
		Iterator<Ort> itrOrt = vecOrt.iterator();
		int i = 0;
		while (itrOrt.hasNext()) {
			Ort o = itrOrt.next();
			if (o.getPlz()==rem.getPlz() && (o.getOrt().compareTo(rem.getOrt())== 0)) {
				vecOrt.remove(i);
			}
			i++;
		}
	}

	public void removeStrasse(Strasse rem) throws Exception {
		Iterator<Strasse> itrStrasse = vecStrasse.iterator();
		int i = 0;
		while (itrStrasse.hasNext()) {
			Strasse s = itrStrasse.next();
			if (s.getPlz()==rem.getPlz() && (s.getStrasse().compareTo(rem.getStrasse())== 0)) {
				vecStrasse.remove(i);
			}
			i++;
		}
	}

	public void removeHaushalt(Haushalt rem) throws Exception {
		Iterator<Haushalt> itrHaushalt = vecHaushalt.iterator();
		int i = 0;
		while (itrHaushalt.hasNext()) {
			Haushalt h = itrHaushalt.next();
			if (h.getHH_ID() == rem.getHH_ID()) {
				vecHaushalt.remove(i);
			}
			i++;
		}
	}

	public void removeMitglied(Mitglied rem) throws Exception {
		Iterator<Mitglied> itrMitglied = vecMitglied.iterator();
		int i = 0;
		while (itrMitglied.hasNext()) {
			Mitglied m = itrMitglied.next();
			if (m.getHH_ID() == rem.getHH_ID() && (m.getMitglieds_id() == rem.getMitglieds_id())) {
				vecMitglied.remove(i);
			}
			i++;
		}
	}

	public void removeVerwaltungspersonal(Verwaltungspersonal rem) throws Exception {
		Iterator<Verwaltungspersonal> itrVerwaltungspersonal = vecVerwaltungspersonal.iterator();
		int i = 0;
		while (itrVerwaltungspersonal.hasNext()) {
			Verwaltungspersonal v = itrVerwaltungspersonal.next();
			if (v.getPersonal_id() == rem.getPersonal_id()) {
				vecVerwaltungspersonal.remove(i);
			}
			i++;
		}
	}

	public void removeWasserstandsmeldung(Wasserstandsmeldung rem) throws Exception {
		Iterator<Wasserstandsmeldung> itrWasserstandsmeldung = vecWasserstandsmeldung.iterator();
		int i = 0;
		while (itrWasserstandsmeldung.hasNext()) {
			Wasserstandsmeldung w = itrWasserstandsmeldung.next();
			if (w.getZaehler_nr() == rem.getZaehler_nr() && (w.getDate() == rem.getDate())) {
				vecWasserstandsmeldung.remove(i);
			}
			i++;
		}
	}

	public void removeWasserzaehler(Wasserzaehler rem) throws Exception {
		Iterator<Wasserzaehler> itrWasserzaehler = vecWasserzaehler.iterator();
		int i = 0;
		while (itrWasserzaehler.hasNext()) {
			Wasserzaehler w = itrWasserzaehler.next();
			if (w.getZaehler_nr() == rem.getZaehler_nr() && (w.getStandort_x() == rem.getStandort_x()) && (w.getStandort_y() == rem.getStandort_y())) {
				vecWasserzaehler.remove(i);
			}
			i++;
		}
	}
}
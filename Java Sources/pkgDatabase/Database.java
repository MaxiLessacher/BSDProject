package pkgDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
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

	//private String connStrg = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	private String connStrg = "jdbc:oracle:thin:@212.152.179.117:1521:ora11g";
	private String user="d5b30";
	private String pwd="d5b30";

	public Database() {

	}

	public void createConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		this.conn = DriverManager.getConnection(connStrg, user, pwd);
	}

	public void loadOrtFromDb() throws SQLException {
		this.vecOrt.clear();
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
		this.vecStrasse.clear();
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
		this.vecAdresse.clear();
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
		this.vecHaushalt.clear();
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
		this.vecMitglied.clear();
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
		this.vecVerwaltungspersonal.clear();
		createConnection();
		String select = "select * from verwaltungspersonal";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addVerwaltungspersonal(new Verwaltungspersonal(rs.getInt("personalid"), rs.getString("name"), rs.getString("abteilung")));
			System.out.println(rs.getInt("personalid") + " " + rs.getString("name") + " " + rs.getString("abteilung"));
		}
	}

	public void loadWasserzaehlerFromDb() throws SQLException {
		this.vecWasserzaehler.clear();
		createConnection();
		//select w.zaehler_nr, t.x, t.y FROM wasserzaehler w  TABLE(SDO_UTIL.GETVERTICES(w.standort)) t
		String select = "select zaehler_nr, hh_id, zaehlerstand, hauptzaehler, t.x, t.y from wasserzaehler w, TABLE(SDO_UTIL.GETVERTICES(w.standort)) t";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			this.addWasserzaehler(new Wasserzaehler(rs.getInt("zaehler_nr"), rs.getInt("hh_id"), rs.getInt("zaehlerstand"),
					rs.getBoolean("hauptzaehler"), rs.getInt("t.x"), rs.getInt("t.y")));
		}
		conn.close();
	}

	public void loadWasserstandsmeldungFromDb() throws SQLException {
		this.vecWasserstandsmeldung.clear();
		createConnection();
		String select = "select * from wasserstandsmeldung";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(select);

		while (rs.next()) {
			LocalDate ldate = rs.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

	public void commit(String command) {
		try {
			createConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(command);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertOrt(Ort newOrt) {
		commit("insert into ort values(" +newOrt.getPlz() + ",'" + newOrt.getOrt() + "')");
	}
	public void insertStrasse(Strasse newStrasse) {
		commit("insert into strasse values(" +newStrasse.getPlz() + ",'" + newStrasse.getStrasse() + "')");
	}
	public void insertAdresse(Adresse newAdresse) {
		commit("insert into adresse values(" +newAdresse.getPlz() + ",'" + newAdresse.getStrasse() + "',"+newAdresse.getHausnummer()+")");
	}
	public void insertHaushalt(Haushalt newHaushalt) {
		int landwirtschaft=0;
		int garten = 0;
		if (newHaushalt.isGarten()) {
			garten = 1;
		}
		if (newHaushalt.isLandwirtschaft()) {
			landwirtschaft = 1;
		}
		commit("insert into haushalt values(" +newHaushalt.getHH_ID() + ",'" + newHaushalt.getStrasse() + "',"+ newHaushalt.getPlz() +", " + newHaushalt.getHausnummer() + ","+newHaushalt.getTuernummer()+","+newHaushalt.getWohnflaeche()+","+landwirtschaft+","+garten+")");
	}
	public void insertMitglied(Mitglied newMitglied) {
		int vorstand = 0;
		if (newMitglied.isHH_Vorstand()) {
			vorstand = 1;
		}
		commit("insert into mitglieder values(" +newMitglied.getMitglieds_id() + ",'" + newMitglied.getName() + "',"+vorstand+","+newMitglied.getHH_ID()+")");
	}
	public void insertVerwaltungspersonal(Verwaltungspersonal newVerwaltungspersonal) {
		commit("insert into verwaltungspersonal values(" +newVerwaltungspersonal.getPersonal_id() + ",'" + newVerwaltungspersonal.getName() + "','"+newVerwaltungspersonal.getAbteilung()+"')");
	}
	public void insertWasserzaehler(Wasserzaehler newWasserzaehler) {
		System.out.println("not implemented");
	}

	public void insertWasserstandsmeldung(Wasserstandsmeldung newWasserstandsmeldung) {
		System.out.println("not implemented");
	}

	//commit("insert into strasse values('" +strasse.getPlz() + "'," + strasse.getPlz() + ")");
	//commit("insert into adresse values('" +adresse.getStrasse() + "'," + adresse.getPlz() + ", adresse.getHausnummer())");
	//commit("insert into haushalt values(" +ort.getPlz() + ",'" + ort.getOrt() + "')");
	//commit("insert into mitglieder values(" +ort.getPlz() + ",'" + ort.getOrt() + "')");
	//commit("insert into verwaltungspersonal values(" +ort.getPlz() + ",'" + ort.getOrt() + "')");
	//commit("insert into wasserzaehler values(" +ort.getPlz() + ",'" + ort.getOrt() + "')");
	//commit("insert into wasserstandsmeldung values(" +ort.getPlz() + ",'" + ort.getOrt() + "')");

	public void updateOrt(Ort oldOrt,Ort newOrt) {
		Iterator<Ort> itrOrte = vecOrt.iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			if (tempOrt.compareTo(oldOrt) == 0) {
				tempOrt = newOrt;
			}
		}
		commit("update ort set name = '" +newOrt.getOrt() + "', plz =" + newOrt.getPlz() + " where plz = " + oldOrt.getPlz());
	}
	public void updateAdresse(Adresse oldAdresse,Adresse newAdresse) {
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		while (itrAdresse.hasNext()) {
			Adresse tempAdresse = itrAdresse.next();
			if (tempAdresse.compareTo(oldAdresse) == 0) {
				tempAdresse = newAdresse;
			}
		}
		commit("insert into ort values(" +newAdresse.getPlz() + ",'" + newAdresse.getStrasse() + "')");
	}
	public void updateHaushalt(Haushalt oldHaushalt,Haushalt newHaushalt) {
		Iterator<Haushalt> itrHaushalte = vecHaushalt.iterator();
		while (itrHaushalte.hasNext()) {
			Haushalt tempHaushalt = itrHaushalte.next();
			if (tempHaushalt.compareTo(oldHaushalt) == 0) {
				tempHaushalt = newHaushalt;
			}
		}
		//commit(update);
	}
	public void updateMitglied(Mitglied oldMitglied,Mitglied newMitglied) {
		Iterator<Mitglied> itrMitglieder = vecMitglied.iterator();
		while (itrMitglieder.hasNext()) {
			Mitglied tempMitglied = itrMitglieder.next();
			if (tempMitglied.compareTo(oldMitglied) == 0) {
				tempMitglied = newMitglied;
			}
		}
		//commit update
	}
	public void updateStrasse(Strasse oldStrasse,Strasse newStrasse) {
		Iterator<Strasse> itrStrasse = vecStrasse.iterator();
		while (itrStrasse.hasNext()) {
			Strasse tempStrasse = itrStrasse.next();
			if (tempStrasse.compareTo(oldStrasse) == 0) {
				tempStrasse = newStrasse;
			}
		}
		// commit update
	}
	public void updateVerwaltungspersonal(Verwaltungspersonal oldVw,Verwaltungspersonal newVw) {
		Iterator<Verwaltungspersonal> itrVerwaltungspersonal = vecVerwaltungspersonal.iterator();
		while (itrVerwaltungspersonal.hasNext()) {
			Verwaltungspersonal tempVerwaltungspersonal = itrVerwaltungspersonal.next();
			if (tempVerwaltungspersonal.compareTo(oldVw) == 0) {
				tempVerwaltungspersonal = newVw;
			}
		}
		// commit update
	}
	public void updateWasserstandsmeldung(Wasserstandsmeldung oldWm,Wasserstandsmeldung newWm) {
		Iterator<Wasserstandsmeldung> itrWm = vecWasserstandsmeldung.iterator();
		while (itrWm.hasNext()) {
			Wasserstandsmeldung tempWm = itrWm.next();
			if (tempWm.compareTo(oldWm) == 0) {
				tempWm = newWm;
			}
		}
		// commit update
	}
	public void updateWasserzaehler(Wasserzaehler oldWz,Wasserzaehler newWz) {
		Iterator<Wasserzaehler> itrWz = vecWasserzaehler.iterator();
		while (itrWz.hasNext()) {
			Wasserzaehler tempWz = itrWz.next();
			if (tempWz.compareTo(oldWz) == 0) {
				tempWz = newWz;
			}
		}
		// commit update
	}

	public void deleteOrt(Ort ort) throws Exception {
		commit("delete from ort where plz = "+ort.getPlz());
		removeOrt(ort);
	}
	public void deleteStrasse(Strasse strasse) throws Exception {
		commit("delete from strasse where plz = "+strasse.getPlz()+" AND name = " +strasse.getStrasse());
		removeStrasse(strasse);
	}
	public void deleteAdresse(Adresse adresse) throws Exception {
		commit("delete from adresse where name = "+adresse.getStrasse()+ " AND plz = " + adresse.getPlz() + " AND nummer = " + adresse.getHausnummer());
		removeAdresse(adresse);
	}
	public void deleteHaushalt(Haushalt haushalt) throws Exception {
		commit("delete from haushalt where hh_id = "+haushalt.getHH_ID());
		removeHaushalt(haushalt);
	}
	public void deleteMitglied(Mitglied mitglied) throws Exception {
		commit("delete from mitglieder where mitglieds_id = "+mitglied.getMitglieds_id() + " AND hh_id = " + mitglied.getHH_ID());
		removeMitglied(mitglied);
	}
	public void deleteVerwaltungspersonal(Verwaltungspersonal vw) throws Exception {
		commit("delete from verwaltungspersonal where personal_id = "+vw.getPersonal_id());
		removeVerwaltungspersonal(vw);;
	}
	public void deleteWasserzaehler(Wasserzaehler wasserzaehler) throws Exception {
		commit("delete from wasserzaehler where zaehler_nr = "+wasserzaehler.getZaehler_nr());
		removeWasserzaehler(wasserzaehler);
	}
	public void deleteWasserstandsmeldung(Wasserstandsmeldung wm) throws Exception {
		commit("delete from wasserstandsmeldung where zaehler_nr = "+wm.getZaehler_nr() + " AND date = " + wm.getDate());
		removeWasserstandsmeldung(wm);
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
		vecAdresse.remove(rem);
	}

	public void removeOrt(Ort rem) throws Exception {
		vecOrt.remove(rem);
	}

	public void removeStrasse(Strasse rem) throws Exception {
		vecStrasse.remove(rem);
	}

	public void removeHaushalt(Haushalt rem) throws Exception {
		vecHaushalt.remove(rem);
	}

	public void removeMitglied(Mitglied rem) throws Exception {
		vecMitglied.remove(rem);
	}

	public void removeVerwaltungspersonal(Verwaltungspersonal rem) throws Exception {
		vecVerwaltungspersonal.remove(rem);
	}

	public void removeWasserstandsmeldung(Wasserstandsmeldung rem) throws Exception {
		vecWasserstandsmeldung.remove(rem);
	}

	public void removeWasserzaehler(Wasserzaehler rem) throws Exception {
		vecWasserzaehler.remove(rem);
	}
}
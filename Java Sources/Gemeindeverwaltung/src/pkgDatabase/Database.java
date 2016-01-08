package pkgDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import pkgClasses.Adresse;
import pkgClasses.Haushalt;
import pkgClasses.Mitglied;
import pkgClasses.Ort;
import pkgClasses.Strasse;
import pkgClasses.Verwaltungspersonal;
import pkgClasses.Wasserstandsmeldung;
import pkgClasses.Wasserzaehler;


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

	private String intConnString = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	private String extConnString = "jdbc:oracle:thin:@212.152.179.117:1521:ora11g";
	private String user="d5b30";
	private String pwd="d5b30";
	private boolean intern = false;

	private static Database instance;
	   
	public static Database getInstance () {
	    if (Database.instance == null) {
	    	Database.instance = new Database ();
	    }
	    return Database.instance;
	}

	public void createConnection() throws SQLException {
		String connStrg = extConnString;
		if (intern) {
			connStrg = intConnString;
		}
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		this.conn = DriverManager.getConnection(connStrg, user, pwd);
	}
	
	public void setIntern(boolean intern) {
		this.intern = intern;
	}
	
	public boolean getIntern() {
		return intern;
	}
	
	private ResultSet getData(String statement) throws SQLException{
		ResultSet retValue = null;
		try{
		createConnection();
		PreparedStatement stmt = conn.prepareStatement(statement,	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		retValue  = stmt.executeQuery();
		}
		catch(Exception e){
			System.out.println("Error in get Data");
			retValue =  null;
		}
		return retValue;
	}

	public void loadOrtFromDb() throws SQLException {
		this.vecOrt.clear();
		ResultSet rs = getData("select * from ort");
		while (rs.next()) {
			this.addOrt(new Ort(rs.getInt("plz"), rs.getString("name")));
		}
	}

	public void loadStrasseFromDb() throws SQLException {
		this.vecStrasse.clear();
		ResultSet rs = getData("select * from strasse");
		while (rs.next()) {
			this.addStrasse(new Strasse(rs.getString("name"), rs.getInt("plz")));
		}
	}

	public void loadAdressenFromDb() throws SQLException {
		this.vecAdresse.clear();
		ResultSet rs = getData("select * from adresse");
		while (rs.next()) {
			this.addAdresse(new Adresse(rs.getInt("plz"), rs.getString("name"), rs.getInt("nummer")));
		}
	}

	public void loadHaushalteFromDb() throws SQLException {
		this.vecHaushalt.clear();
		ResultSet rs = getData("select * from haushalt");
		while (rs.next()) {
			boolean boolland = false;
			boolean boolgart = false;
			if (rs.getInt("landwirtschaft") == 1) {
				boolland = true;
			}
			if (rs.getInt("garten") == 1) {
				boolgart = true;
			}
			this.addHaushalt(new Haushalt(rs.getInt("hh_id"), rs.getString("name"), rs.getInt("plz"), rs.getInt("nummer"),
					rs.getInt("tuernr"), rs.getInt("wohnflaeche"), boolland, boolgart));
		}
	}

	public void loadMitgliederFromDb() throws SQLException {
		this.vecMitglied.clear();
		ResultSet rs = getData("select * from mitglieder");
		while (rs.next()) {
			boolean bool = false;
			if (rs.getInt("HH_Vorstand") == 1) {
				bool = true;
			}
			this.addMitglied(new Mitglied(rs.getInt("mitglieds_id"), rs.getString("name"), bool,
					rs.getInt("hh_id")));
		}
	}

	public void loadVerwaltungspersonalFromDb() throws SQLException {
		this.vecVerwaltungspersonal.clear();
		ResultSet rs = getData("select * from verwaltungspersonal");
		while (rs.next()) {
			this.addVerwaltungspersonal(new Verwaltungspersonal(rs.getInt("personalid"), rs.getString("name"), rs.getString("abteilung")));
		}
	}

	public void loadWasserzaehlerFromDb() throws SQLException {
		this.vecWasserzaehler.clear();
		ResultSet rs = getData("select zaehler_nr, hh_id, zaehlerstand, hauptzaehler, t.x, t.y from wasserzaehler w, TABLE(SDO_UTIL.GETVERTICES(w.standort)) t order by zaehler_nr");
		while (rs.next()) {
			boolean bool = false;
			if (rs.getInt(4) == 1) {
				bool = true;
			}
			this.addWasserzaehler(new Wasserzaehler(rs.getInt(1), rs.getInt(2), rs.getInt(3),
					bool, rs.getInt(5), rs.getInt(6)));
		}
	}

	public void loadWasserstandsmeldungFromDb() throws SQLException {
		this.vecWasserstandsmeldung.clear();
		ResultSet rs = getData("select * from wasserstandsmeldung");
		while (rs.next()) {
			this.addWasserstandsmeldung(new Wasserstandsmeldung(rs.getDate("datum"), rs.getInt("zaehler_nr"), rs.getInt("neuZaehlerstand")));
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

	public void insertOrt(Ort newOrt) throws SQLException {
		createConnection();
		try {
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement("insert into ort values(?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stmt.setInt(1, newOrt.getPlz());
		stmt.setString(2, newOrt.getOrt());
		stmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("error occured in insertOrt");
		}
	}
	public void insertStrasse(Strasse newStrasse) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into strasse values(?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, newStrasse.getStrasse());
			stmt.setInt(2, newStrasse.getPlz());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertStrasse");
			}
	}
	public void insertAdresse(Adresse newAdresse) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into adresse values(?,?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newAdresse.getPlz());
			stmt.setString(2, newAdresse.getStrasse());
			stmt.setInt(3, newAdresse.getHausnummer());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertAdresse");
			}
	}
	public void insertHaushalt(Haushalt newHaushalt) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into haushalt values(?,?,?,?,?,?,?,?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newHaushalt.getHH_ID());
			stmt.setString(2, newHaushalt.getStrasse());
			stmt.setInt(3, newHaushalt.getPlz());
			stmt.setInt(4, newHaushalt.getHausnummer());
			stmt.setInt(5, newHaushalt.getTuernummer());
			stmt.setInt(6, newHaushalt.getWohnflaeche());
			stmt.setBoolean(7, newHaushalt.isLandwirtschaft());
			stmt.setBoolean(8, newHaushalt.isGarten());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertHaushalt");
				e.printStackTrace();
			}
	}
	public void insertMitglied(Mitglied newMitglied) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into mitglieder values(?,?,?,?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newMitglied.getMitglieds_id());
			stmt.setString(2, newMitglied.getName());
			stmt.setBoolean(3, newMitglied.isHH_Vorstand());
			stmt.setInt(4, newMitglied.getHH_ID());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertMitglied");
				e.printStackTrace();
			}
	}
	public void insertVerwaltungspersonal(Verwaltungspersonal newVw) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into verwaltungspersonal values(?,?,?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newVw.getPersonal_id());
			stmt.setString(2, newVw.getName());
			stmt.setString(3, newVw.getAbteilung());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void insertWasserzaehler(Wasserzaehler newWz) throws SQLException {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("INSERT INTO Wasserzaehler values (?, ?, ?, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(?, ?, NULL), NULL, NULL), ?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newWz.getZaehler_nr());
			stmt.setInt(2, newWz.getHH_ID());
			stmt.setInt(3, newWz.getZaehlerstand());
			stmt.setInt(4, newWz.getStandort_x());
			stmt.setInt(5, newWz.getStandort_y());
			stmt.setBoolean(6, newWz.isHauptzaehler());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void insertWasserstandsmeldung(Wasserstandsmeldung newWm) throws SQLException {
		createConnection();
		try {
			java.sql.Date date = new java.sql.Date(newWm.getDatum().getTime());
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("INSERT INTO Wasserstandsmeldung VALUES(?,?,?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setDate(1, date);
			stmt.setInt(2, newWm.getZaehlerNr());
			stmt.setInt(3, newWm.getNeuZaehlerstand());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in insertVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void updateOrt(Ort oldOrt,Ort newOrt) throws SQLException {
		Iterator<Ort> itrOrte = vecOrt.iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			if (tempOrt.compareTo(oldOrt) == 0) {
				tempOrt = newOrt;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update ort set name = ?, plz = ? where plz = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, newOrt.getOrt());
			stmt.setInt(2, newOrt.getPlz());
			stmt.setInt(3, oldOrt.getPlz());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateOrt");
				e.printStackTrace();
			}
	}
	public void updateAdresse(Adresse oldAdresse,Adresse newAdresse) throws SQLException {
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		while (itrAdresse.hasNext()) {
			Adresse tempAdresse = itrAdresse.next();
			if (tempAdresse.compareTo(oldAdresse) == 0) {
				tempAdresse = newAdresse;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update adresse set plz = ?, name = ?, nummer = ? where plz = ? AND name = ? AND nummer = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newAdresse.getPlz());
			stmt.setString(2, newAdresse.getStrasse());
			stmt.setInt(3, newAdresse.getHausnummer());
			stmt.setInt(4, oldAdresse.getPlz());
			stmt.setString(5, oldAdresse.getStrasse());
			stmt.setInt(6, oldAdresse.getHausnummer());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateAdresse");
				e.printStackTrace();
			}
	}
	public void updateHaushalt(Haushalt oldHaushalt,Haushalt newHaushalt) throws SQLException {
		Iterator<Haushalt> itrHaushalte = vecHaushalt.iterator();
		while (itrHaushalte.hasNext()) {
			Haushalt tempHaushalt = itrHaushalte.next();
			if (tempHaushalt.compareTo(oldHaushalt) == 0) {
				tempHaushalt = newHaushalt;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update haushalt set hh_id=?, plz = ?, name=?, nummer=?, tuernr=?, wohnflaeche=?, landwirtschaft = ?, garten = ? where hh_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newHaushalt.getHH_ID());
			stmt.setInt(2, newHaushalt.getPlz());
			stmt.setString(3, newHaushalt.getStrasse());
			stmt.setInt(4, newHaushalt.getHausnummer());
			stmt.setInt(5, newHaushalt.getTuernummer());
			stmt.setInt(6, newHaushalt.getWohnflaeche());
			stmt.setBoolean(7, newHaushalt.isLandwirtschaft());
			stmt.setBoolean(8, newHaushalt.isGarten());
			stmt.setInt(9, oldHaushalt.getHH_ID());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}
	public void updateMitglied(Mitglied oldMitglied,Mitglied newMitglied) throws SQLException {
		Iterator<Mitglied> itrMitglieder = vecMitglied.iterator();
		while (itrMitglieder.hasNext()) {
			Mitglied tempMitglied = itrMitglieder.next();
			if (tempMitglied.compareTo(oldMitglied) == 0) {
				tempMitglied = newMitglied;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update mitglieder set mitglieds_id=?, name = ?, hh_vorstand=?, hh_id=? where mitglieds_id = ? AND hh_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newMitglied.getHH_ID());
			stmt.setString(2, newMitglied.getName());
			stmt.setBoolean(3, newMitglied.isHH_Vorstand());
			stmt.setInt(4, newMitglied.getHH_ID());
			stmt.setInt(5, oldMitglied.getMitglieds_id());
			stmt.setInt(6, oldMitglied.getHH_ID());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}
	public void updateStrasse(Strasse oldStrasse,Strasse newStrasse) throws SQLException {
		Iterator<Strasse> itrStrasse = vecStrasse.iterator();
		while (itrStrasse.hasNext()) {
			Strasse tempStrasse = itrStrasse.next();
			if (tempStrasse.compareTo(oldStrasse) == 0) {
				tempStrasse = newStrasse;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update strasse set name=?, plz = ? where name = ? AND plz = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, newStrasse.getStrasse());
			stmt.setInt(2, newStrasse.getPlz());
			stmt.setString(3, oldStrasse.getStrasse());
			stmt.setInt(4, oldStrasse.getPlz());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}
	public void updateVerwaltungspersonal(Verwaltungspersonal oldVw,Verwaltungspersonal newVw) throws SQLException {
		Iterator<Verwaltungspersonal> itrVerwaltungspersonal = vecVerwaltungspersonal.iterator();
		while (itrVerwaltungspersonal.hasNext()) {
			Verwaltungspersonal tempVerwaltungspersonal = itrVerwaltungspersonal.next();
			if (tempVerwaltungspersonal.compareTo(oldVw) == 0) {
				tempVerwaltungspersonal = newVw;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update verwaltungspersonal set personalid=?, name = ?, abteilung=? where personal_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newVw.getPersonal_id());
			stmt.setString(2, newVw.getName());
			stmt.setString(3, newVw.getAbteilung());
			stmt.setInt(4, oldVw.getPersonal_id());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}
	public void updateWasserstandsmeldung(Wasserstandsmeldung oldWm,Wasserstandsmeldung newWm) throws SQLException {
		Iterator<Wasserstandsmeldung> itrWm = vecWasserstandsmeldung.iterator();
		while (itrWm.hasNext()) {
			Wasserstandsmeldung tempWm = itrWm.next();
			if (tempWm.compareTo(oldWm) == 0) {
				tempWm = newWm;
			}
		}
		createConnection();
		try {
			java.sql.Date oldDate = new java.sql.Date(oldWm.getDatum().getTime());
			java.sql.Date newDate = new java.sql.Date(newWm.getDatum().getTime());
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update wasserstandsmeldung set zaehler_nr=?, neuZaehlerstand = ?, date=? where date = ? AND zaehler_nr = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newWm.getZaehlerNr());
			stmt.setInt(2, newWm.getNeuZaehlerstand());
			stmt.setDate(3, newDate);
			stmt.setDate(4, oldDate);
			stmt.setInt(5, oldWm.getZaehlerNr());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}
	public void updateWasserzaehler(Wasserzaehler oldWz,Wasserzaehler newWz) throws SQLException {
		Iterator<Wasserzaehler> itrWz = vecWasserzaehler.iterator();
		while (itrWz.hasNext()) {
			Wasserzaehler tempWz = itrWz.next();
			if (tempWz.compareTo(oldWz) == 0) {
				tempWz = newWz;
			}
		}
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update wasserzaehler set zaehler_nr=?, neuZaehlerstand=? where zaehler_nr = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newWz.getZaehler_nr());
			stmt.setInt(2, newWz.getZaehlerstand());
			stmt.setInt(3, oldWz.getZaehler_nr());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in updateHaushalt");
				e.printStackTrace();
			}
	}

	public void deleteOrt(Ort ort) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from ort where plz = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, ort.getPlz());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteOrt");
				e.printStackTrace();
			}
		removeOrt(ort);
	}
	public void deleteStrasse(Strasse strasse) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from strasse where plz = ? AND name= ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, strasse.getPlz());
			stmt.setString(2, strasse.getStrasse());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteStrasse");
				e.printStackTrace();
			}
		removeStrasse(strasse);
	}
	public void deleteAdresse(Adresse adresse) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from adresse where name = ? AND plz = ? AND nummer=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, adresse.getStrasse());
			stmt.setInt(2, adresse.getPlz());
			stmt.setInt(3, adresse.getHausnummer());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteAdresse");
				e.printStackTrace();
			}
		removeAdresse(adresse);
	}
	public void deleteHaushalt(Haushalt haushalt) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from haushalt where hh_id=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, haushalt.getHH_ID());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteHaushalt");
				e.printStackTrace();
			}
		removeHaushalt(haushalt);
	}
	public void deleteMitglied(Mitglied mitglied) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from mitglieder where mitglieds_id = ? AND hh_id = ? AND nummer=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, mitglied.getMitglieds_id());
			stmt.setInt(2, mitglied.getHH_ID());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteMitglied");
				e.printStackTrace();
			}
		removeMitglied(mitglied);
	}
	public void deleteVerwaltungspersonal(Verwaltungspersonal vw) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from verwaltungspersonal where personalid=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			System.out.println(vw.getPersonal_id());
			stmt.setInt(1, vw.getPersonal_id());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteVerwaltungspersonal");
				e.printStackTrace();
			}
		removeVerwaltungspersonal(vw);;
	}
	public void deleteWasserzaehler(Wasserzaehler wasserzaehler) throws Exception {
		createConnection();
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from wasserzaehler where zaehler_nr=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, wasserzaehler.getZaehler_nr());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteWasserzaehler");
				e.printStackTrace();
			}
		removeWasserzaehler(wasserzaehler);
	}
	public void deleteWasserstandsmeldung(Wasserstandsmeldung wm) throws Exception {
		createConnection();
		try {
			java.sql.Date date = new java.sql.Date(wm.getDatum().getTime());
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("delete from adresse where date = ? AND zaehler_nr = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setDate(1, date);
			stmt.setInt(2, wm.getZaehlerNr());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in deleteWasserstandsmeldung");
				e.printStackTrace();
			}
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
	
	public boolean ortExists(Ort ort) {
		boolean retValue = false;
		Iterator<Ort> itrOrte = vecOrt.iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			if (tempOrt.compareTo(ort) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean streetExists(Strasse strasse) {
		boolean retValue = false;
		Iterator<Strasse> itrStrassen = vecStrasse.iterator();
		while (itrStrassen.hasNext()) {
			Strasse tempStrasse = itrStrassen.next();
			if (tempStrasse.compareTo(strasse) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean addressExists(Adresse adresse) {
		boolean retValue = false;
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		while (itrAdresse.hasNext()) {
			Adresse tempAdresse = itrAdresse.next();
			if (tempAdresse.compareTo(adresse) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean haushaltExists(Haushalt haushalt) {
		boolean retValue = false;
		Iterator<Haushalt> itrHaushalt = vecHaushalt.iterator();
		while (itrHaushalt.hasNext()) {
			Haushalt tempHaushalt = itrHaushalt.next();
			if (tempHaushalt.compareTo(haushalt) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean memberExists(Mitglied mitglied) {
		boolean retValue = false;
		Iterator<Mitglied> itrMitglied = vecMitglied.iterator();
		while (itrMitglied.hasNext()) {
			Mitglied tempMitglied = itrMitglied.next();
			if (tempMitglied.compareTo(mitglied) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean vwExists(Verwaltungspersonal Vw) {
		boolean retValue = false;
		Iterator<Verwaltungspersonal> itrVw = vecVerwaltungspersonal.iterator();
		while (itrVw.hasNext()) {
			Verwaltungspersonal tempVw = itrVw.next();
			if (tempVw.compareTo(Vw) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean wasserzaehlerExists(Wasserzaehler Wz) {
		boolean retValue = false;
		Iterator<Wasserzaehler> itrWz = vecWasserzaehler.iterator();
		while (itrWz.hasNext()) {
			Wasserzaehler tempWz = itrWz.next();
			if (tempWz.compareTo(Wz) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
	
	public boolean wmExists(Wasserstandsmeldung Wm) {
		boolean retValue = false;
		Iterator<Wasserstandsmeldung> itrWm = vecWasserstandsmeldung.iterator();
		while (itrWm.hasNext()) {
			Wasserstandsmeldung tempWm = itrWm.next();
			if (tempWm.compareTo(Wm) == 0) {
				retValue = true;
			}
		}
		return retValue;
	}
}
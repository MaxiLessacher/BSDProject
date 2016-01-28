package pkgDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	private DataSource ds = null;
	private Vector<Ort> vecOrt = new Vector<Ort>();
	private Vector<Strasse> vecStrasse = new Vector<Strasse>();
	private Vector<Adresse> vecAdresse = new Vector<Adresse>();
	private Vector<Haushalt> vecHaushalt = new Vector<Haushalt>();
	private Vector<Mitglied> vecMitglied = new Vector<Mitglied>();
	private Vector<Verwaltungspersonal> vecVerwaltungspersonal = new Vector<Verwaltungspersonal>();
	private Vector<Wasserzaehler> vecWasserzaehler = new Vector<Wasserzaehler>();
	private Vector<Wasserstandsmeldung> vecWasserstandsmeldung = new Vector<Wasserstandsmeldung>();

	private static Database instance;
	   
	public static Database getInstance () {
	    if (Database.instance == null) {
	    	Database.instance = new Database ();
	    }
	    return Database.instance;
	}
	
	private Database() {
		super();
		try {
			InitialContext ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/resService");
			this.createConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Connection createConnection() throws Exception {
	   	conn = ds.getConnection();
	   	
	   	return conn;
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

	public void loadOrtFromDb() {
		try {
			this.vecOrt.clear();
			ResultSet rs = getData("select * from ort");
			while (rs.next()) {
				this.vecOrt.add(new Ort(rs.getInt("plz"), rs.getString("name")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading orte");
		}
	}

	public void loadStrasseFromDb() {
		try {
			this.vecStrasse.clear();
			ResultSet rs = getData("select * from strasse");
			while (rs.next()) {
				this.vecStrasse.add(new Strasse(rs.getString("name"), rs.getInt("plz")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading strassen");
		}
	}

	public void loadAdressenFromDb() {
		try {
			this.vecAdresse.clear();
			ResultSet rs = getData("select * from adresse");
			while (rs.next()) {
				this.vecAdresse.add(new Adresse(rs.getInt("plz"), rs.getString("name"), rs.getInt("nummer")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading adressen");
		}
	}

	public void loadHaushalteFromDb() {
		try {
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
				this.vecHaushalt.add(new Haushalt(rs.getInt("hh_id"), rs.getString("name"), rs.getInt("plz"), rs.getInt("nummer"),
						rs.getInt("tuernr"), rs.getInt("wohnflaeche"), boolland, boolgart));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading haushalte");
		}
	}

	public void loadMitgliederFromDb() {
		try {
			this.vecMitglied.clear();
			ResultSet rs = getData("select * from mitglieder");
			while (rs.next()) {
				boolean bool = false;
				if (rs.getInt("HH_Vorstand") == 1) {
					bool = true;
				}
				this.vecMitglied.add(new Mitglied(rs.getInt("mitglieds_id"), rs.getString("name"), bool,
						rs.getInt("hh_id")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading mitglieder");
		}
	}

	public void loadVerwaltungspersonalFromDb() {
		try {
			this.vecVerwaltungspersonal.clear();
			ResultSet rs = getData("select * from verwaltungspersonal");
			while (rs.next()) {
				this.vecVerwaltungspersonal.add(new Verwaltungspersonal(rs.getInt("personalid"), rs.getString("name"), rs.getString("abteilung")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading verwaltungspersonal");
		}
	}

	public void loadWasserzaehlerFromDb() {
		try {
			this.vecWasserzaehler.clear();
			ResultSet rs = getData("select zaehler_nr, hh_id, zaehlerstand, hauptzaehler, t.x, t.y from wasserzaehler w, TABLE(SDO_UTIL.GETVERTICES(w.standort)) t order by zaehler_nr");
			while (rs.next()) {
				boolean bool = false;
				if (rs.getInt(4) == 1) {
					bool = true;
				}
				this.vecWasserzaehler.add(new Wasserzaehler(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						bool, rs.getInt(5), rs.getInt(6)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading wasserzähler");
		}
	}

	public void loadWasserstandsmeldungFromDb() {
		try {
			this.vecWasserstandsmeldung.clear();
			ResultSet rs = getData("select * from wasserstandsmeldung");
			while (rs.next()) {
				this.vecWasserstandsmeldung.add(new Wasserstandsmeldung(rs.getDate("datum"), rs.getInt("zaehler_nr"), rs.getInt("neuZaehlerstand")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error while loading wasserstandsmeldung");
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Vector<Ort> getVecOrt() {
		loadOrtFromDb();
		return vecOrt;
	}

	public void setVecOrt(Vector<Ort> vecOrt) {
		this.vecOrt = vecOrt;
	}

	public Vector<Strasse> getVecStrasse() {
		loadStrasseFromDb();
		return vecStrasse;
	}

	public void setVecStrasse(Vector<Strasse> vecStrasse) {
		this.vecStrasse = vecStrasse;
	}

	public Vector<Adresse> getVecAdresse() {
		loadAdressenFromDb();
		return vecAdresse;
	}

	public void setVecAdresse(Vector<Adresse> vecAdresse) {
		this.vecAdresse = vecAdresse;
	}

	public Vector<Haushalt> getVecHaushalt() {
		loadHaushalteFromDb();
		return vecHaushalt;
	}

	public void setVecHaushalt(Vector<Haushalt> vecHaushalt) {
		this.vecHaushalt = vecHaushalt;
	}

	public Vector<Mitglied> getVecMitglied() {
		loadMitgliederFromDb();
		return vecMitglied;
	}

	public void setVecMitglied(Vector<Mitglied> vecMitglied) {
		this.vecMitglied = vecMitglied;
	}

	public Vector<Verwaltungspersonal> getVecVerwaltungspersonal() {
		loadVerwaltungspersonalFromDb();
		return vecVerwaltungspersonal;
	}

	public void setVecVerwaltungspersonal(Vector<Verwaltungspersonal> vecVerwaltungspersonal) {
		this.vecVerwaltungspersonal = vecVerwaltungspersonal;
	}

	public Vector<Wasserzaehler> getVecWasserzaehler() {
		loadWasserzaehlerFromDb();
		return vecWasserzaehler;
	}

	public void setVecWasserzaehler(Vector<Wasserzaehler> vecWasserzaehler) {
		this.vecWasserzaehler = vecWasserzaehler;
	}

	public Vector<Wasserstandsmeldung> getVecWasserstandsmeldung() {
		loadWasserstandsmeldungFromDb();
		return vecWasserstandsmeldung;
	}

	public void setVecWasserstandsmeldung(Vector<Wasserstandsmeldung> vecWasserstandsmeldung) {
		this.vecWasserstandsmeldung = vecWasserstandsmeldung;
	}

	public void addOrt(Ort newOrt) {
		try {
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement("insert into ort values(?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stmt.setInt(1, newOrt.getPlz());
		stmt.setString(2, newOrt.getOrt());
		stmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("error occured in addOrt");
		}
	}
	public void addStrasse(Strasse newStrasse) {
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into strasse values(?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, newStrasse.getStrasse());
			stmt.setInt(2, newStrasse.getPlz());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in addStrasse");
			}
	}
	public void addAdresse(Adresse newAdresse) {
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into adresse values(?,?,?)",	ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newAdresse.getPlz());
			stmt.setString(2, newAdresse.getStrasse());
			stmt.setInt(3, newAdresse.getHausnummer());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in addAdresse");
			}
	}
	public void addHaushalt(Haushalt newHaushalt) {
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
				System.out.println("error occured in addHaushalt");
				e.printStackTrace();
			}
	}
	public void addMitglied(Mitglied newMitglied) {
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
				System.out.println("error occured in addMitglied");
				e.printStackTrace();
			}
	}
	public void addVerwaltungspersonal(Verwaltungspersonal newVw) {
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("insert into verwaltungspersonal values(?,?,?)", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, newVw.getPersonal_id());
			stmt.setString(2, newVw.getName());
			stmt.setString(3, newVw.getAbteilung());
			stmt.executeUpdate();
			}
			catch (Exception e) {
				System.out.println("error occured in addVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void addWasserzaehler(Wasserzaehler newWz) {
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
				System.out.println("error occured in addVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void addWasserstandsmeldung(Wasserstandsmeldung newWm) {
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
				System.out.println("error occured in addVerwaltungspersonal");
				e.printStackTrace();
			}
	}
	public void updateOrt(Ort oldOrt,Ort newOrt) {
		Iterator<Ort> itrOrte = vecOrt.iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			if (tempOrt.compareTo(oldOrt) == 0) {
				tempOrt = newOrt;
			}
		}
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
	public void updateAdresse(Adresse oldAdresse,Adresse newAdresse) {
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		while (itrAdresse.hasNext()) {
			Adresse tempAdresse = itrAdresse.next();
			if (tempAdresse.compareTo(oldAdresse) == 0) {
				tempAdresse = newAdresse;
			}
		}
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
	public void updateHaushalt(Haushalt oldHaushalt,Haushalt newHaushalt) {
		Iterator<Haushalt> itrHaushalte = vecHaushalt.iterator();
		while (itrHaushalte.hasNext()) {
			Haushalt tempHaushalt = itrHaushalte.next();
			if (tempHaushalt.compareTo(oldHaushalt) == 0) {
				tempHaushalt = newHaushalt;
			}
		}
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
	public void updateMitglied(Mitglied oldMitglied,Mitglied newMitglied) {
		Iterator<Mitglied> itrMitglieder = vecMitglied.iterator();
		while (itrMitglieder.hasNext()) {
			Mitglied tempMitglied = itrMitglieder.next();
			if (tempMitglied.compareTo(oldMitglied) == 0) {
				tempMitglied = newMitglied;
			}
		}
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
	public void updateStrasse(Strasse oldStrasse,Strasse newStrasse) {
		Iterator<Strasse> itrStrasse = vecStrasse.iterator();
		while (itrStrasse.hasNext()) {
			Strasse tempStrasse = itrStrasse.next();
			if (tempStrasse.compareTo(oldStrasse) == 0) {
				tempStrasse = newStrasse;
			}
		}
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
	public void updateVerwaltungspersonal(Verwaltungspersonal oldVw,Verwaltungspersonal newVw) {
		Iterator<Verwaltungspersonal> itrVerwaltungspersonal = vecVerwaltungspersonal.iterator();
		while (itrVerwaltungspersonal.hasNext()) {
			Verwaltungspersonal tempVerwaltungspersonal = itrVerwaltungspersonal.next();
			if (tempVerwaltungspersonal.compareTo(oldVw) == 0) {
				tempVerwaltungspersonal = newVw;
			}
		}
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("update verwaltungspersonal set personalid=?, name = ?, abteilung=? where personalid = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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
	public void updateWasserstandsmeldung(Wasserstandsmeldung oldWm,Wasserstandsmeldung newWm) {
		Iterator<Wasserstandsmeldung> itrWm = vecWasserstandsmeldung.iterator();
		while (itrWm.hasNext()) {
			Wasserstandsmeldung tempWm = itrWm.next();
			if (tempWm.compareTo(oldWm) == 0) {
				tempWm = newWm;
			}
		}
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
	public void updateWasserzaehler(Wasserzaehler oldWz,Wasserzaehler newWz) {
		Iterator<Wasserzaehler> itrWz = vecWasserzaehler.iterator();
		while (itrWz.hasNext()) {
			Wasserzaehler tempWz = itrWz.next();
			if (tempWz.compareTo(oldWz) == 0) {
				tempWz = newWz;
			}
		}
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

	public void deleteOrt(Ort ort) {
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
		vecOrt.remove(ort);
	}
	public void deleteStrasse(Strasse strasse) {
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
		vecStrasse.remove(strasse);
	}
	public void deleteAdresse(Adresse adresse) {
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
		vecAdresse.remove(adresse);
	}
	public void deleteHaushalt(Haushalt haushalt) {
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
		vecHaushalt.remove(haushalt);
	}
	public void deleteMitglied(Mitglied mitglied) {
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
		vecMitglied.remove(mitglied);
	}
	public void deleteVerwaltungspersonal(Verwaltungspersonal vw) {
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
		vecVerwaltungspersonal.remove(vw);;
	}
	public void deleteWasserzaehler(Wasserzaehler wasserzaehler) {
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
		vecWasserzaehler.remove(wasserzaehler);
	}
	public void deleteWasserstandsmeldung(Wasserstandsmeldung wm) {
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
		vecWasserstandsmeldung.remove(wm);
	}
}
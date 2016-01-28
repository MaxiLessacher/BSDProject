package pkgDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import pkgClasses.Wasserstandsmeldung;



public class Database {
	private Connection conn;
	private Vector<Wasserstandsmeldung> vecWasserstandsmeldung = new Vector<Wasserstandsmeldung>();
	private Vector<Integer> vecWasserzaehler = new Vector<Integer>();
	
	private String intConnString = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	private String extConnString = "jdbc:oracle:thin:@212.152.179.117:1521:ora11g";
	private String user="d5b30";
	private String pwd="d5b30";
	private boolean intern = true;

	private static Database instance;
	   
	public static Database getInstance () {
	    if (Database.instance == null) {
	    	Database.instance = new Database ();
	    }
	    return Database.instance;
	}

	public void createConnection() throws SQLException {
		String connStrg = intConnString;
		if (!intern) {
			connStrg = extConnString;
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

	public void loadWasserzaehlerFromDb() throws SQLException {
		this.vecWasserzaehler.clear();
		ResultSet rs = getData("select zaehler_nr from wasserzaehler");
		while (rs.next()) {
			vecWasserzaehler.add(rs.getInt(1));
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

	public Vector<Wasserstandsmeldung> getVecWasserstandsmeldung() {
		return vecWasserstandsmeldung;
	}

	public void setVecWasserstandsmeldung(Vector<Wasserstandsmeldung> vecWasserstandsmeldung) {
		this.vecWasserstandsmeldung = vecWasserstandsmeldung;
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
	/*
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
	}*/
	
	public void addWasserstandsmeldung(Wasserstandsmeldung wasserstandsmeldung) {
		this.vecWasserstandsmeldung.add(wasserstandsmeldung);
	}
	
	public void removeWasserstandsmeldung(Wasserstandsmeldung rem) throws Exception {
		vecWasserstandsmeldung.remove(rem);
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

	public Vector<Integer> getVecWasserzaehler() {
		return vecWasserzaehler;
	}
}
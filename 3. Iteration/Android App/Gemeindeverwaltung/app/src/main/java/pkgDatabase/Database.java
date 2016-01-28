package pkgDatabase;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class Database extends Application implements Serializable {
	private final String FILE_NAME = "Data.txt";

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

	public void addOrt(Ort newOrt) {
		if (newOrt!=null && !vecOrt.contains(newOrt)) {
			vecOrt.add(newOrt);
		}
	}
	public void addStrasse(Strasse newStrasse) {
		if (newStrasse!=null && !vecStrasse.contains(newStrasse)) {
			vecStrasse.add(newStrasse);
		}
	}
	public void addAdresse(Adresse newAdresse) {
		if (newAdresse!=null && !vecAdresse.contains(newAdresse)) {
			vecAdresse.add(newAdresse);
		}
	}
	public void addHaushalt(Haushalt newHaushalt) {
		if (newHaushalt!=null && !vecHaushalt.contains(newHaushalt)) {
			vecHaushalt.add(newHaushalt);
		}
	}
	public void addMitglied(Mitglied newMitglied) {
		if (newMitglied!=null && !vecMitglied.contains(newMitglied)) {
			vecMitglied.add(newMitglied);
		}
	}
	public void addVerwaltungspersonal(Verwaltungspersonal newVw) {
		if (newVw!=null && !vecVerwaltungspersonal.contains(newVw)) {
			vecVerwaltungspersonal.add(newVw);
		}
	}
	public void addWasserzaehler(Wasserzaehler newWz) {
		if (newWz!=null && !vecWasserzaehler.contains(newWz)) {
			vecWasserzaehler.add(newWz);
		}
	}
	public void addWasserstandsmeldung(Wasserstandsmeldung newWm) {
		if (newWm!=null && !vecWasserstandsmeldung.contains(newWm)) {
			vecWasserstandsmeldung.add(newWm);
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
	}
	public void updateAdresse(Adresse oldAdresse,Adresse newAdresse) {
		Iterator<Adresse> itrAdresse = vecAdresse.iterator();
		while (itrAdresse.hasNext()) {
			Adresse tempAdresse = itrAdresse.next();
			if (tempAdresse.compareTo(oldAdresse) == 0) {
				tempAdresse = newAdresse;
			}
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
	}
	public void updateMitglied(Mitglied oldMitglied,Mitglied newMitglied) {
		Iterator<Mitglied> itrMitglieder = vecMitglied.iterator();
		while (itrMitglieder.hasNext()) {
			Mitglied tempMitglied = itrMitglieder.next();
			if (tempMitglied.compareTo(oldMitglied) == 0) {
				tempMitglied = newMitglied;
			}
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
	}
	public void updateVerwaltungspersonal(Verwaltungspersonal oldVw,Verwaltungspersonal newVw) {
		Iterator<Verwaltungspersonal> itrVerwaltungspersonal = vecVerwaltungspersonal.iterator();
		while (itrVerwaltungspersonal.hasNext()) {
			Verwaltungspersonal tempVerwaltungspersonal = itrVerwaltungspersonal.next();
			if (tempVerwaltungspersonal.compareTo(oldVw) == 0) {
				tempVerwaltungspersonal = newVw;
			}
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
	}
	public void updateWasserzaehler(Wasserzaehler oldWz,Wasserzaehler newWz) {
		Iterator<Wasserzaehler> itrWz = vecWasserzaehler.iterator();
		while (itrWz.hasNext()) {
			Wasserzaehler tempWz = itrWz.next();
			if (tempWz.compareTo(oldWz) == 0) {
				tempWz = newWz;
			}
		}
	}

	public void deleteOrt(Ort ort) {
		vecOrt.remove(ort);
	}
	public void deleteStrasse(Strasse strasse) {
		vecStrasse.remove(strasse);
	}
	public void deleteAdresse(Adresse adresse) {
		vecAdresse.remove(adresse);
	}
	public void deleteHaushalt(Haushalt haushalt) {
		vecHaushalt.remove(haushalt);
	}
	public void deleteMitglied(Mitglied mitglied) {
		vecMitglied.remove(mitglied);
	}
	public void deleteVerwaltungspersonal(Verwaltungspersonal vw) {
		vecVerwaltungspersonal.remove(vw);;
	}
	public void deleteWasserzaehler(Wasserzaehler wasserzaehler) {
		vecWasserzaehler.remove(wasserzaehler);
	}
	public void deleteWasserstandsmeldung(Wasserstandsmeldung wm) {
		vecWasserstandsmeldung.remove(wm);
	}

	/*public void saveFile() {
		try {
			File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS.toString()), FILE_NAME);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
			oos.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFile() {
		try {
			FileInputStream fis = null;
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.instance = (Database) ois.readObject();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/
}
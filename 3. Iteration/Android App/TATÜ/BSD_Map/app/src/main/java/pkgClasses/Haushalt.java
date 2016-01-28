package pkgClasses;

import android.graphics.Bitmap;

/**
 * Created by David on 22.01.2016.
 */
public class Haushalt {
    private String name;
    private String strasse;
    private int hausnummer;
    private int plz;
    private Bitmap pic;

    public Haushalt() {

    }
    public Haushalt(String name, int plz, String strasse, int hausnummer, Bitmap pic) {
        this.name = name;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.pic = pic;
        this.plz = plz;
    }

    public String getName() {
        return name;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}

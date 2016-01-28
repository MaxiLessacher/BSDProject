package pkgClasses;

import android.app.Application;
import android.provider.ContactsContract;

import java.util.Vector;

/**
 * Created by David on 22.01.2016.
 */
public class Database extends Application {
    Vector<Haushalt> vecHaushalt = new Vector<>();

    private static Database instance = null;

    private Database() {
        super();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addHaushalt(Haushalt h) {
        vecHaushalt.add(h);
    }

    public Vector<Haushalt> getVecHaushalt() {
        return this.vecHaushalt;
    }

    public void setVecHaushalt(Vector<Haushalt> vecH) {
        this.vecHaushalt = vecH;
    }
}

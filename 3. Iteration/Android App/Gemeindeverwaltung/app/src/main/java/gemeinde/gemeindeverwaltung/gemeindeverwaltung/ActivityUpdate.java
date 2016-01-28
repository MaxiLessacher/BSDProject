package gemeinde.gemeindeverwaltung.gemeindeverwaltung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Vector;

import pkgClasses.Adresse;
import pkgClasses.Haushalt;
import pkgClasses.Mitglied;
import pkgClasses.Ort;
import pkgClasses.Strasse;
import pkgClasses.Verwaltungspersonal;
import pkgClasses.Wasserstandsmeldung;
import pkgClasses.Wasserzaehler;
import pkgDatabase.Database;

public class ActivityUpdate extends AppCompatActivity implements View.OnClickListener{
    private Button btnAdd = null,
            btnCancel = null;
    private TextView lblArg1,
            lblArg2,
            lblArg3,
            lblArg4,
            lblArg5,
            lblArg6,
            lblArg7,
            lblArg8;
    private EditText txtArg1,
            txtArg2,
            txtArg3,
            txtArg4;
    private CheckBox chbox1,
            chbox2;
    private Spinner  spinner1,
            spinner2,
            spinner3;
    private DatePicker datePicker;
    private TableLayout tableInsert;
    private Object object = null;
    private Database database=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = Database.getInstance();
        object = getIntent().getExtras().get("object");
        if (object != null) {
            getAllViews();
        } else {
            closeActivity();
        }
    }

    private void getAllViews() {
        btnAdd = (Button) this.findViewById(R.id.btnAddInfo);
        btnCancel = (Button) this.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        tableInsert = (TableLayout) this.findViewById(R.id.tableInsert);

        if (object instanceof Ort) {
            initOrt((Ort) object);
        }
        else if (object instanceof Strasse) {
            initStrasse((Strasse)object);
        }
        else if (object instanceof Adresse) {
            initAdresse((Adresse)object);
        }
        else if (object instanceof Haushalt) {
            initHaushalt((Haushalt)object);
        }
        else if (object instanceof Mitglied) {
            initMitglied((Mitglied)object);
        }
        else if (object instanceof Wasserstandsmeldung) {
            initWasserstandsmeldung((Wasserstandsmeldung)object);
        }
        else if (object instanceof Wasserzaehler) {
            initWasserzaehler((Wasserzaehler)object);
        }
        else if (object instanceof Verwaltungspersonal) {
            initVerwaltungspersonal((Verwaltungspersonal)object);
        }
    }

    private void initOrt(Ort ort) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblOrt);
        txtArg1 = new EditText(this);
        txtArg2 = new EditText(this);
        txtArg1.setText(ort.getPlz());
        txtArg2.setText(ort.getOrt());
        txtArg1.setGravity(Gravity.CENTER_HORIZONTAL);
        txtArg2.setGravity(Gravity.CENTER_HORIZONTAL);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(txtArg1);
        row2.addView(lblArg2);
        row2.addView(txtArg2);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
    }
    private void initStrasse(Strasse strasse) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblStrasse);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
        txtArg1.setText(strasse.getStrasse());
        Vector<Integer> vecPLZ = null;
        Vector<Ort> vecOrt = database.getVecOrt();
        for (int i = 0; i < vecOrt.size();i++) {
            vecPLZ.add(vecOrt.get(i).getPlz());
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecPLZ
        );
        spinner1.setAdapter(adapter);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(spinner1);
        row2.addView(lblArg2);
        row2.addView(txtArg1);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
    }
    private void initAdresse(Adresse adresse) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblStrasse);
        lblArg3.setText(R.string.lblHausnummer);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
        spinner2 = new Spinner(this);
        txtArg1.setText(adresse.getHausnummer());
        Vector<Strasse> vecStrassen = database.getVecStrasse();
        Vector<Ort> vecOrt = database.getVecOrt();
        Vector<String> vecStrasse = new Vector<>();
        Vector<Integer> vecPLZ = new Vector<>();
        for (int i = 0; i < vecOrt.size(); i++) {
            vecPLZ.add(vecOrt.get(i).getPlz());
        }
        for (int i = 0; i < vecStrassen.size(); i++) {
            vecStrasse.add(vecStrassen.get(i).getStrasse());
        }
        ArrayAdapter<String> strasseAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, vecStrasse
        );
        ArrayAdapter<Integer> plzAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecPLZ
        );
        spinner1.setAdapter(plzAdapter);
        spinner2.setAdapter(strasseAdapter);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(spinner1);
        row2.addView(lblArg2);
        row2.addView(spinner2);
        row3.addView(lblArg3);
        row3.addView(txtArg1);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
    }
    private void initHaushalt(Haushalt haushalt) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg4 = new TextView(this);
        lblArg5 = new TextView(this);
        lblArg6 = new TextView(this);
        lblArg7 = new TextView(this);
        lblArg8 = new TextView(this);
        lblArg1.setText(R.string.lblHH_ID);
        lblArg2.setText(R.string.lblPLZ);
        lblArg3.setText(R.string.lblStrasse);
        lblArg4.setText(R.string.lblHausnummer);
        lblArg5.setText(R.string.lblTuernummer);
        lblArg6.setText(R.string.lblWohnflaeche);
        lblArg7.setText(R.string.lblLandwirtschaft);
        lblArg8.setText(R.string.lblGarten);
        txtArg1 = new EditText(this);
        txtArg1.setText(haushalt.getHH_ID());
        txtArg2 = new EditText(this);
        txtArg2.setText(haushalt.getTuernummer());
        txtArg3 = new EditText(this);
        txtArg3.setText(haushalt.getWohnflaeche());
        spinner1 = new Spinner(this);
        spinner2 = new Spinner(this);
        spinner3 = new Spinner(this);
        chbox1 = new CheckBox(this);
        chbox1.setChecked(haushalt.isLandwirtschaft());
        chbox2 = new CheckBox(this);
        chbox2.setChecked(haushalt.isGarten());
        Vector<Ort> vecOrt = database.getVecOrt();
        Vector<Strasse> vecStrassen = database.getVecStrasse();
        Vector<Adresse> vecAdresse = database.getVecAdresse();
        Vector<Integer> vecPLZ = new Vector<>();
        Vector<Integer> vecHausnummer = new Vector<>();
        Vector<String> vecStrasse = new Vector<>();
        for (int i = 0; i < vecOrt.size(); i++) {
            vecPLZ.add(vecOrt.get(i).getPlz());
        }
        for (int i = 0; i < vecStrassen.size(); i++) {
            vecStrasse.add(vecStrassen.get(i).getStrasse());
        }
        for (int i = 0; i < vecAdresse.size(); i++) {
            vecHausnummer.add(vecAdresse.get(i).getHausnummer());
        }
        ArrayAdapter<String> strasseAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, vecStrasse
        );
        ArrayAdapter<Integer> hausnummerAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecHausnummer
        );
        ArrayAdapter<Integer> plzAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecPLZ
        );
        spinner1.setAdapter(plzAdapter);
        spinner2.setAdapter(strasseAdapter);
        spinner3.setAdapter(hausnummerAdapter);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        TableRow row4 = new TableRow(this);
        TableRow row5 = new TableRow(this);
        TableRow row6 = new TableRow(this);
        TableRow row7 = new TableRow(this);
        TableRow row8 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(txtArg1);
        row2.addView(lblArg2);
        row2.addView(spinner1);
        row3.addView(lblArg3);
        row3.addView(spinner2);
        row4.addView(lblArg4);
        row4.addView(spinner3);
        row5.addView(lblArg5);
        row5.addView(txtArg2);
        row6.addView(lblArg6);
        row6.addView(txtArg3);
        row7.addView(lblArg7);
        row7.addView(chbox1);
        row8.addView(lblArg8);
        row8.addView(chbox2);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
        tableInsert.addView(row4);
        tableInsert.addView(row5);
        tableInsert.addView(row6);
        tableInsert.addView(row7);
        tableInsert.addView(row8);
    }
    private void initMitglied(Mitglied mitglied) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg4 = new TextView(this);
        lblArg1.setText(R.string.lblMitgliedsID);
        lblArg2.setText(R.string.lblName);
        lblArg3.setText(R.string.lblHHVorstand);
        lblArg4.setText(R.string.lblHH_ID);
        txtArg1 = new EditText(this);
        txtArg1.setText(mitglied.getMitglieds_id());
        txtArg2 = new EditText(this);
        txtArg2.setText(mitglied.getName());
        spinner1 = new Spinner(this);
        Vector<Integer> vecHHID = new Vector<>();
        Vector<Haushalt> vecHaushalt = database.getVecHaushalt();
        for (int i = 0; i < vecHaushalt.size(); i++) {
            vecHHID.add(vecHaushalt.get(i).getHH_ID());
        }
        ArrayAdapter<Integer> HHadapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecHHID
        );
        spinner1.setAdapter(HHadapter);
        chbox1 = new CheckBox(this);
        chbox1.setChecked(mitglied.isHH_Vorstand());
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        TableRow row4 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(txtArg1);
        row2.addView(lblArg2);
        row2.addView(txtArg2);
        row3.addView(lblArg3);
        row3.addView(chbox1);
        row4.addView(lblArg4);
        row4.addView(spinner1);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
        tableInsert.addView(row4);
    }
    private void initWasserzaehler(Wasserzaehler wz) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg4 = new TextView(this);
        lblArg5 = new TextView(this);
        lblArg6 = new TextView(this);
        lblArg1.setText(R.string.lblZaehlerNr);
        lblArg2.setText(R.string.lblHH_ID);
        lblArg3.setText(R.string.lblZaehlerstand);
        lblArg4.setText(R.string.lblHHVorstand);
        lblArg5.setText(R.string.lblX);
        lblArg6.setText(R.string.lblY);
        txtArg1 = new EditText(this);
        txtArg1.setText(wz.getZaehler_nr());
        txtArg2 = new EditText(this);
        txtArg2.setText(wz.getZaehlerstand());
        txtArg3 = new EditText(this);
        txtArg3.setText(wz.getStandort_x());
        txtArg4 = new EditText(this);
        txtArg4.setText(wz.getStandort_y());
        spinner1 = new Spinner(this);
        Vector<Integer> vecHHID = new Vector<>();
        Vector<Haushalt> vecHaushalt = database.getVecHaushalt();
        for (int i = 0; i < vecHaushalt.size(); i++) {
            vecHHID.add(vecHaushalt.get(i).getHH_ID());
        }
        ArrayAdapter<Integer> HHadapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecHHID
        );
        spinner1.setAdapter(HHadapter);
        chbox1 = new CheckBox(this);
        chbox1.setChecked(wz.isHauptzaehler());
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        TableRow row4 = new TableRow(this);
        TableRow row5 = new TableRow(this);
        TableRow row6 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(txtArg1);
        row2.addView(lblArg2);
        row2.addView(spinner1);
        row3.addView(lblArg3);
        row3.addView(txtArg2);
        row4.addView(lblArg4);
        row4.addView(chbox1);
        row5.addView(lblArg5);
        row5.addView(txtArg3);
        row6.addView(lblArg6);
        row6.addView(txtArg4);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
        tableInsert.addView(row4);
        tableInsert.addView(row5);
        tableInsert.addView(row6);
    }
    private void initWasserstandsmeldung(Wasserstandsmeldung ws) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblZaehlerNr);
        lblArg2.setText(R.string.lblneuZaehlerstand);
        lblArg3.setText(R.string.lblDatum);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
        Vector<Integer> vecZaehlerNr = new Vector<>();
        Vector<Wasserzaehler> vecWasserzaehler = database.getVecWasserzaehler();
        for (int i = 0; i < vecWasserzaehler.size(); i++) {
            vecZaehlerNr.add(vecWasserzaehler.get(i).getZaehler_nr());
        }
        ArrayAdapter<Integer> HHadapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_dropdown_item, vecZaehlerNr
        );
        spinner1.setAdapter(HHadapter);
        datePicker = new DatePicker(this);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(spinner1);
        row2.addView(lblArg2);
        row2.addView(txtArg1);
        row3.addView(lblArg3);
        row3.addView(datePicker);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
    }
    private void initVerwaltungspersonal(Verwaltungspersonal vw) {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblPersonalID);
        lblArg1.setText(R.string.lblName);
        lblArg1.setText(R.string.lblAbteilung);
        txtArg1 = new EditText(this);
        txtArg1.setText(vw.getPersonal_id());
        txtArg2 = new EditText(this);
        txtArg2.setText(vw.getName());
        txtArg3 = new EditText(this);
        txtArg3.setText(vw.getAbteilung());
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(txtArg1);
        row2.addView(lblArg2);
        row2.addView(txtArg2);
        row3.addView(lblArg3);
        row3.addView(txtArg3);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
        tableInsert.addView(row3);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAddInfo:
                if (object instanceof Ort) {
                    database.updateOrt((Ort)object,new Ort(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString()));
                }
                else if (object instanceof Strasse) {
                    database.updateStrasse((Strasse)object,new Strasse(txtArg1.getText().toString(), Integer.parseInt(spinner1.getSelectedItem().toString())));
                }
                else if (object instanceof Adresse) {
                    database.updateAdresse((Adresse)object,new Adresse(Integer.parseInt(spinner1.getSelectedItem().toString()), spinner2.getSelectedItem().toString(), Integer.parseInt(txtArg1.getText().toString())));
                }
                else if (object instanceof Haushalt) {
                    database.updateHaushalt((Haushalt)object,new Haushalt(Integer.parseInt(txtArg1.getText().toString()), spinner1.getSelectedItem().toString(), Integer.parseInt(spinner2.getSelectedItem().toString()), Integer.parseInt(spinner3.getSelectedItem().toString()), Integer.parseInt(txtArg2.getText().toString()), Integer.parseInt(txtArg3.getText().toString()), chbox1.isSelected(), chbox2.isSelected()));
                }
                else if (object instanceof Mitglied) {
                    database.updateMitglied((Mitglied)object,new Mitglied(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString(), chbox1.isSelected(), Integer.parseInt(spinner1.getSelectedItem().toString())));
                }
                else if (object instanceof Wasserzaehler) {
                    database.updateWasserzaehler((Wasserzaehler)object,new Wasserzaehler(Integer.parseInt(txtArg1.getText().toString()), Integer.parseInt(spinner1.getSelectedItem().toString()), Integer.parseInt(txtArg2.getText().toString()), chbox1.isChecked(), Integer.parseInt(txtArg3.getText().toString()), Integer.parseInt(txtArg4.getText().toString())));
                }
                else if (object instanceof Wasserstandsmeldung) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                    Date date = calendar.getTime();
                    database.updateWasserstandsmeldung((Wasserstandsmeldung) object, new Wasserstandsmeldung(date, Integer.parseInt(spinner1.getSelectedItem().toString()), Integer.parseInt(txtArg1.getText().toString())));
                }
                else if (object instanceof Verwaltungspersonal) {
                    database.updateVerwaltungspersonal((Verwaltungspersonal)object, new Verwaltungspersonal(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString(), txtArg3.getText().toString()));
                }
            case R.id.btnCancel:
                closeActivity();
                break;
        }
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }
}

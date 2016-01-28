package gemeinde.gemeindeverwaltung.gemeindeverwaltung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
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

import pkgClasses.Adresse;
import pkgClasses.Haushalt;
import pkgClasses.Mitglied;
import pkgClasses.Ort;
import pkgClasses.Strasse;
import pkgClasses.Verwaltungspersonal;
import pkgClasses.Wasserstandsmeldung;
import pkgClasses.Wasserzaehler;
import pkgDatabase.Database;

public class ActivityAdd extends AppCompatActivity implements View.OnClickListener{
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
    private String caller = null;
    private Database database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = Database.getInstance();
        caller = getIntent().getExtras().getString("Caller");
        getAllViews();
    }

    private void getAllViews() {
        btnAdd = (Button) this.findViewById(R.id.btnAddInfo);
        btnCancel = (Button) this.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        tableInsert = (TableLayout) this.findViewById(R.id.tableInsert);

        switch (caller) {
            case "Ort":
                initOrt();
                break;
            case "Strasse":
                initStrasse();
                break;
            case "Adresse":
                initAdresse();
                break;
            case "Haushalt":
                initHaushalt();
                break;
            case "Mitglied":
                initMitglied();
                break;
            case "Wasserzaehler":
                initWasserzaehler();
                break;
            case "Wasserstandsmeldung":
                initWasserstandsmeldung();
                break;
            case "Verwaltungspersonal":
                initVerwaltungspersonal();
                break;
        }
    }

    private void initOrt() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblOrt);
        txtArg1 = new EditText(this);
        txtArg2 = new EditText(this);
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
    private void initStrasse() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblStrasse);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        row1.addView(lblArg1);
        row1.addView(spinner1);
        row2.addView(lblArg2);
        row2.addView(txtArg1);
        tableInsert.addView(row1);
        tableInsert.addView(row2);
    }
    private void initAdresse() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblPLZ);
        lblArg2.setText(R.string.lblStrasse);
        lblArg3.setText(R.string.lblHausnummer);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
        spinner2 = new Spinner(this);
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
    private void initHaushalt() {
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
        txtArg2 = new EditText(this);
        txtArg3 = new EditText(this);
        spinner1 = new Spinner(this);
        spinner2 = new Spinner(this);
        spinner3 = new Spinner(this);
        chbox1 = new CheckBox(this);
        chbox2 = new CheckBox(this);
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
    private void initMitglied() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg4 = new TextView(this);
        lblArg1.setText(R.string.lblMitgliedsID);
        lblArg2.setText(R.string.lblName);
        lblArg3.setText(R.string.lblHHVorstand);
        lblArg4.setText(R.string.lblHH_ID);
        txtArg1 = new EditText(this);
        txtArg2 = new EditText(this);
        spinner1 = new Spinner(this);
        chbox1 = new CheckBox(this);
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
    private void initWasserzaehler() {
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
        txtArg2 = new EditText(this);
        txtArg3 = new EditText(this);
        txtArg4 = new EditText(this);
        spinner1 = new Spinner(this);
        chbox1 = new CheckBox(this);
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
    private void initWasserstandsmeldung() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblZaehlerNr);
        lblArg2.setText(R.string.lblneuZaehlerstand);
        lblArg3.setText(R.string.lblDatum);
        txtArg1 = new EditText(this);
        spinner1 = new Spinner(this);
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
    private void initVerwaltungspersonal() {
        lblArg1 = new TextView(this);
        lblArg2 = new TextView(this);
        lblArg3 = new TextView(this);
        lblArg1.setText(R.string.lblPersonalID);
        lblArg1.setText(R.string.lblName);
        lblArg1.setText(R.string.lblAbteilung);
        txtArg1 = new EditText(this);
        txtArg2 = new EditText(this);
        txtArg3 = new EditText(this);
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
                switch (caller) {
                    case "Ort":
                        database.addOrt(new Ort(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString()));
                        break;
                    case "Strasse":
                        database.addStrasse(new Strasse(txtArg1.getText().toString(), Integer.parseInt(spinner1.getSelectedItem().toString())));
                        break;
                    case "Adresse":
                        database.addAdresse(new Adresse(Integer.parseInt(spinner1.getSelectedItem().toString()), spinner2.getSelectedItem().toString(), Integer.parseInt(txtArg1.getText().toString())));
                        break;
                    case "Haushalt":
                        database.addHaushalt(new Haushalt(Integer.parseInt(txtArg1.getText().toString()), spinner1.getSelectedItem().toString(), Integer.parseInt(spinner2.getSelectedItem().toString()), Integer.parseInt(spinner3.getSelectedItem().toString()), Integer.parseInt(txtArg2.getText().toString()), Integer.parseInt(txtArg3.getText().toString()), chbox1.isSelected(), chbox2.isSelected()));
                        break;
                    case "Mitglied":
                        database.addMitglied(new Mitglied(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString(), chbox1.isSelected(), Integer.parseInt(spinner1.getSelectedItem().toString())));
                        break;
                    case "Wasserzaehler":
                        database.addWasserzaehler(new Wasserzaehler(Integer.parseInt(txtArg1.getText().toString()), Integer.parseInt(spinner1.getSelectedItem().toString()), Integer.parseInt(txtArg2.getText().toString()), chbox1.isChecked(), Integer.parseInt(txtArg3.getText().toString()), Integer.parseInt(txtArg4.getText().toString())));
                        break;
                    case "Wasserstandsmeldung":
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        Date date = calendar.getTime();
                        database.addWasserstandsmeldung(new Wasserstandsmeldung(date, Integer.parseInt(spinner1.getSelectedItem().toString()), Integer.parseInt(txtArg1.getText().toString())));
                        break;
                    case "Verwaltungspersonal":
                        database.addVerwaltungspersonal(new Verwaltungspersonal(Integer.parseInt(txtArg1.getText().toString()), txtArg2.getText().toString(), txtArg3.getText().toString()));
                        break;
                }
                break;
            case R.id.btnCancel:
                break;
        }
        this.finish();
    }
}

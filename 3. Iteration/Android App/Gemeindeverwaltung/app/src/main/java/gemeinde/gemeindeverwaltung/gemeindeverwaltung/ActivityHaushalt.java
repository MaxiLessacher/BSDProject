package gemeinde.gemeindeverwaltung.gemeindeverwaltung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static gemeinde.gemeindeverwaltung.gemeindeverwaltung.R.drawable.column_header;

public class ActivityHaushalt extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableHaushalt = null;
    TableRow head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haushalt);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colPLZ = new TextView(this);
        TextView colOrt = new TextView(this);
        TextView colStrasse = new TextView(this);
        TextView colHausnummer = new TextView(this);
        TextView colTuernummer = new TextView(this);
        TextView colWohnflaeche = new TextView(this);
        TextView colLandwirtschaft = new TextView(this);
        TextView colGarten = new TextView(this);
        colPLZ.setText("HH_ID");
        colPLZ.setGravity(Gravity.CENTER_HORIZONTAL);
        colPLZ.setTextSize(18);
        colPLZ.setBackgroundResource(column_header);
        colPLZ.setPadding(7, 1, 7, 1);
        colOrt.setText("PLZ");
        colOrt.setGravity(Gravity.CENTER_HORIZONTAL);
        colOrt.setTextSize(18);
        colOrt.setBackgroundResource(column_header);
        colOrt.setPadding(7, 1, 7, 1);
        colStrasse.setText("Strasse");
        colStrasse.setGravity(Gravity.CENTER_HORIZONTAL);
        colStrasse.setTextSize(18);
        colStrasse.setBackgroundResource(column_header);
        colStrasse.setPadding(7, 1, 7, 1);
        colHausnummer.setText("Hausnummer");
        colHausnummer.setGravity(Gravity.CENTER_HORIZONTAL);
        colHausnummer.setTextSize(18);
        colHausnummer.setBackgroundResource(column_header);
        colHausnummer.setPadding(7, 1, 7, 1);
        colTuernummer.setText("Türnummer");
        colTuernummer.setGravity(Gravity.CENTER_HORIZONTAL);
        colTuernummer.setTextSize(18);
        colTuernummer.setBackgroundResource(column_header);
        colTuernummer.setPadding(7, 1, 7, 1);
        colWohnflaeche.setText("Wohnfläche");
        colWohnflaeche.setGravity(Gravity.CENTER_HORIZONTAL);
        colWohnflaeche.setTextSize(18);
        colWohnflaeche.setBackgroundResource(column_header);
        colWohnflaeche.setPadding(7, 1, 7, 1);
        colLandwirtschaft.setText("Landwirtschaft");
        colLandwirtschaft.setGravity(Gravity.CENTER_HORIZONTAL);
        colLandwirtschaft.setTextSize(18);
        colLandwirtschaft.setBackgroundResource(column_header);
        colLandwirtschaft.setPadding(7, 1, 7, 1);
        colGarten.setText("Garten");
        colGarten.setGravity(Gravity.CENTER_HORIZONTAL);
        colGarten.setTextSize(18);
        colGarten.setBackgroundResource(column_header);
        colGarten.setPadding(7, 1, 7, 1);
        head.addView(colPLZ);
        head.addView(colOrt);
        head.addView(colStrasse);
        head.addView(colHausnummer);
        head.addView(colTuernummer);
        head.addView(colWohnflaeche);
        head.addView(colLandwirtschaft);
        head.addView(colGarten);
        tableHaushalt.addView(head);
    }

    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableHaushalt = (TableLayout) this.findViewById(R.id.tableHaushalt);

        this.btnAdd.setOnClickListener(this);
        this.btnUpdate.setOnClickListener(this);
        this.btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnAdd:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller","Haushalt");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Haushalt");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

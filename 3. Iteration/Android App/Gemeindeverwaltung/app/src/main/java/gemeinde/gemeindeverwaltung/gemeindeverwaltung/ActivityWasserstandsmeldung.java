package gemeinde.gemeindeverwaltung.gemeindeverwaltung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static gemeinde.gemeindeverwaltung.gemeindeverwaltung.R.drawable.column_header;

public class ActivityWasserstandsmeldung extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableWasserstandsmeldung = null;
    TableRow head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasserstandsmeldung);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colZaehlerNr = new TextView(this);
        TextView colDatum = new TextView(this);
        TextView colNeuZaehlerstand = new TextView(this);
        colZaehlerNr.setText("Zählernummer");
        colZaehlerNr.setGravity(Gravity.CENTER_HORIZONTAL);
        colZaehlerNr.setTextSize(18);
        colZaehlerNr.setBackgroundResource(column_header);
        colNeuZaehlerstand.setText("neuer Zählerstand");
        colNeuZaehlerstand.setGravity(Gravity.CENTER_HORIZONTAL);
        colNeuZaehlerstand.setTextSize(18);
        colNeuZaehlerstand.setBackgroundResource(column_header);
        colDatum.setText("Datum");
        colDatum.setGravity(Gravity.CENTER_HORIZONTAL);
        colDatum.setTextSize(18);
        colDatum.setBackgroundResource(column_header);
        head.addView(colZaehlerNr);
        head.addView(colDatum);
        head.addView(colNeuZaehlerstand);
        tableWasserstandsmeldung.addView(head);
    }

    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableWasserstandsmeldung = (TableLayout) this.findViewById(R.id.tableWasserstandsmeldung);

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
                intent.putExtra("Caller", "Wasserstandsmeldung");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Wasserstandsmeldung");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

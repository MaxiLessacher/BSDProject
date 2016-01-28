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

public class ActivityWasserzaehler extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableWasserzaehler = null;
    TableRow head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasserzaehler);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colZaehlerNr = new TextView(this);
        TextView col_HH_ID = new TextView(this);
        TextView colZaehlerstand = new TextView(this);
        TextView colHauptzaehler = new TextView(this);
        TextView colX = new TextView(this);
        TextView colY = new TextView(this);
        colZaehlerNr.setText("Zählernummer");
        colZaehlerNr.setGravity(Gravity.CENTER_HORIZONTAL);
        colZaehlerNr.setTextSize(18);
        colZaehlerNr.setBackgroundResource(column_header);
        col_HH_ID.setText("HaushaltsID");
        col_HH_ID.setGravity(Gravity.CENTER_HORIZONTAL);
        col_HH_ID.setTextSize(18);
        col_HH_ID.setBackgroundResource(column_header);
        colZaehlerstand.setText("Zählerstand");
        colZaehlerstand.setGravity(Gravity.CENTER_HORIZONTAL);
        colZaehlerstand.setTextSize(18);
        colZaehlerstand.setBackgroundResource(column_header);
        colHauptzaehler.setText("Hauptzähler");
        colHauptzaehler.setGravity(Gravity.CENTER_HORIZONTAL);
        colHauptzaehler.setTextSize(18);
        colHauptzaehler.setBackgroundResource(column_header);
        colX.setText("X-Koordinate");
        colX.setGravity(Gravity.CENTER_HORIZONTAL);
        colX.setTextSize(18);
        colX.setBackgroundResource(column_header);
        colY.setText("Y-Koordinate");
        colY.setGravity(Gravity.CENTER_HORIZONTAL);
        colY.setTextSize(18);
        colY.setBackgroundResource(column_header);
        head.addView(colZaehlerNr);
        head.addView(col_HH_ID);
        head.addView(colZaehlerstand);
        head.addView(colHauptzaehler);
        head.addView(colX);
        head.addView(colY);
        tableWasserzaehler.addView(head);
    }

    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableWasserzaehler = (TableLayout) this.findViewById(R.id.tableWasserzaehler);

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
                intent.putExtra("Caller","Wasserzaehler");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Wasserzaehler");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

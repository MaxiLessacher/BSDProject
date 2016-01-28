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

public class ActivityMitglied extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableMitglied = null;
    TableRow head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitglied);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colMitglieds_id = new TextView(this);
        TextView colName = new TextView(this);
        TextView col_HH_ID = new TextView(this);
        TextView colisVorstand = new TextView(this);
        colMitglieds_id.setText("Mitglieds_ID");
        colMitglieds_id.setGravity(Gravity.CENTER_HORIZONTAL);
        colMitglieds_id.setTextSize(18);
        colMitglieds_id.setBackgroundResource(column_header);
        colName.setText("Name");
        colName.setGravity(Gravity.CENTER_HORIZONTAL);
        colName.setTextSize(18);
        colName.setBackgroundResource(column_header);
        col_HH_ID.setText("HH_ID");
        col_HH_ID.setGravity(Gravity.CENTER_HORIZONTAL);
        col_HH_ID.setTextSize(18);
        col_HH_ID.setBackgroundResource(column_header);
        colisVorstand.setText("Vorstand");
        colisVorstand.setGravity(Gravity.CENTER_HORIZONTAL);
        colisVorstand.setTextSize(18);
        colisVorstand.setBackgroundResource(column_header);
        head.addView(colMitglieds_id);
        head.addView(colName);
        head.addView(col_HH_ID);
        head.addView(colisVorstand);
        tableMitglied.addView(head);
    }

    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableMitglied = (TableLayout) this.findViewById(R.id.tableMitglied);

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
                intent.putExtra("Caller","Mitglied");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Mitglied");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

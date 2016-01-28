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

public class ActivityVerwaltungspersonal extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableVerwaltungspersonal = null;
    TableRow head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verwaltungspersonal);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colPersonalID = new TextView(this);
        TextView colName = new TextView(this);
        TextView colAbteilung = new TextView(this);
        colPersonalID.setText("Personal_ID");
        colPersonalID.setGravity(Gravity.CENTER_HORIZONTAL);
        colPersonalID.setTextSize(18);
        colPersonalID.setBackgroundResource(column_header);
        colName.setText("Name");
        colName.setGravity(Gravity.CENTER_HORIZONTAL);
        colName.setTextSize(18);
        colName.setBackgroundResource(column_header);
        colAbteilung.setText("Abteilung");
        colAbteilung.setGravity(Gravity.CENTER_HORIZONTAL);
        colAbteilung.setTextSize(18);
        colAbteilung.setBackgroundResource(column_header);
        head.addView(colPersonalID);
        head.addView(colName);
        head.addView(colAbteilung);
        tableVerwaltungspersonal.addView(head);
    }

    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableVerwaltungspersonal = (TableLayout) this.findViewById(R.id.tableVerwaltungspersonal);

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
                intent.putExtra("Caller","Verwaltungspersonal");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Verwaltungspersonal");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

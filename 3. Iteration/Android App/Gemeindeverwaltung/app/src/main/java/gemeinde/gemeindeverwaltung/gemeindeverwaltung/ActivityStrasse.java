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

public class ActivityStrasse extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd = null,
            btnUpdate = null,
            btnDelete = null;
    TableLayout tableStrasse = null;
    TableRow head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strasse);
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lp);
        TextView colPLZ = new TextView(this);
        TextView colStrasse = new TextView(this);
        colPLZ.setText("PLZ");
        colPLZ.setGravity(Gravity.CENTER_HORIZONTAL);
        colPLZ.setTextSize(18);
        colPLZ.setBackgroundResource(column_header);
        colStrasse.setText("Strasse");
        colStrasse.setGravity(Gravity.CENTER_HORIZONTAL);
        colStrasse.setTextSize(18);
        colStrasse.setBackgroundResource(column_header);
        head.addView(colPLZ);
        head.addView(colStrasse);
        tableStrasse.addView(head);
    }


    private void getAllViews() {
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);
        this.btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.btnDelete = (Button) this.findViewById(R.id.btnDelete);
        this.tableStrasse = (TableLayout) this.findViewById(R.id.tableStrasse);

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
                intent.putExtra("Caller","Strasse");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller", "Strasse");
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

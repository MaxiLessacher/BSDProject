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
import android.widget.Toast;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

import pkgClasses.Ort;
import pkgController.ControllerGetData;
import pkgController.ControllerSendData;
import pkgDatabase.Database;
import pkgEnum.ENUM_SERVICE;

import static gemeinde.gemeindeverwaltung.gemeindeverwaltung.R.drawable.column_header;

public class ActivityOrt extends AppCompatActivity implements View.OnClickListener {
    Button  btnAdd,
            btnUpdate,
            btnDelete;
    TableLayout tableOrt;
    TableRow head;
    //ControllerGetData controllerGetData = null;
    //ControllerSendData controllerSendData = null;
    Vector<Ort> ortVector = null;
    Database database = null;
    Ort actual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ort);
        database = Database.getInstance();
        ortVector = database.getVecOrt();
        getAllViews();
        initTable();
    }

    public void initTable() {
        this.head = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            head.setLayoutParams(lp);
            TextView colPLZ = new TextView(this);
            TextView colOrt = new TextView(this);
            colPLZ.setText("PLZ");
            colPLZ.setGravity(Gravity.CENTER_HORIZONTAL);
            colPLZ.setTextSize(18);
            colPLZ.setBackgroundResource(column_header);
            colOrt.setText("Ort");
            colOrt.setGravity(Gravity.CENTER_HORIZONTAL);
            colOrt.setTextSize(18);
            colOrt.setBackgroundResource(column_header);
            head.addView(colPLZ);
            head.addView(colOrt);
            tableOrt.addView(head);
            try {
            /*controllerGetData.execute(ENUM_SERVICE.SERVICE_ORT.toString());
            Vector<Object> result = controllerGetData.get();
            Toast.makeText(this,result.get(0).toString() + "\n" + result.get(0).toString(),Toast.LENGTH_LONG).show();*/
                ortVector = database.getVecOrt();

                for (int i = 0; i < ortVector.size(); i++) {
                    Ort o = ortVector.get(i);
                    TableRow row = new TableRow(this);
                    final TextView txt1 = new TextView(this);
                    final TextView txt2 = new TextView(this);
                    txt1.setText(Integer.toString(o.getPlz()));
                    txt2.setText(o.getOrt());
                    txt1.setPadding(7, 1, 7, 1);
                    txt2.setPadding(7, 1, 7, 1);
                    row.addView(txt1);
                    row.addView(txt2);
                    row.setId(i);
                    tableOrt.addView(row);
                }
        } catch (Exception e) {
            Toast.makeText(this, "error occured: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void getAllViews() {
        btnAdd = (Button) this.findViewById(R.id.btnAdd);
        btnDelete = (Button) this.findViewById(R.id.btnDelete);
        btnUpdate = (Button) this.findViewById(R.id.btnUpdate);
        this.tableOrt = (TableLayout) this.findViewById(R.id.tableOrt);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnAdd:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra("Caller","Ort");
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                intent = new Intent(this, ActivityAdd.class);
                Toast.makeText(this, actual.toString(), Toast.LENGTH_LONG).show();
                intent.putExtra("Caller", actual);
                startActivity(intent);
                break;
            case R.id.btnDelete:
                break;
        }
    }
}

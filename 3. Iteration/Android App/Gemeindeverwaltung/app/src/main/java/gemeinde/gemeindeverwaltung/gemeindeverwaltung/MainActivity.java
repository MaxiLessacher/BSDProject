package gemeinde.gemeindeverwaltung.gemeindeverwaltung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOrt;
    private Button btnStrasse;
    private Button btnAdresse;
    private Button btnHaushalt;
    private Button btnMitglied;
    private Button btnWasserzaehler;
    private Button btnWasserstandsmeldung;
    private Button btnVerwaltungspersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllViews();
    }

    private void getAllViews() {
        this.btnOrt = (Button) this.findViewById(R.id.btnOrt);
        this.btnStrasse = (Button) this.findViewById(R.id.btnStrasse);
        this.btnAdresse = (Button) this.findViewById(R.id.btnAdresse);
        this.btnHaushalt = (Button) this.findViewById(R.id.btnHaushalt);
        this.btnMitglied = (Button) this.findViewById(R.id.btnMitglied);
        this.btnWasserzaehler = (Button) this.findViewById(R.id.btnWasserzaehler);
        this.btnWasserstandsmeldung = (Button) this.findViewById(R.id.btnWasserstandsmeldung);
        this.btnVerwaltungspersonal = (Button) this.findViewById(R.id.btnVerwaltungspersonal);

        btnOrt.setOnClickListener(this);
        btnStrasse.setOnClickListener(this);
        btnAdresse.setOnClickListener(this);
        btnHaushalt.setOnClickListener(this);
        btnMitglied.setOnClickListener(this);
        btnWasserzaehler.setOnClickListener(this);
        btnWasserstandsmeldung.setOnClickListener(this);
        btnVerwaltungspersonal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnOrt:
                intent = new Intent(this, ActivityOrt.class);
                startActivity(intent);
                break;
            case R.id.btnStrasse:
                intent = new Intent(this, ActivityStrasse.class);
                startActivity(intent);
                break;
            case R.id.btnAdresse:
                intent = new Intent(this, ActivityAdresse.class);
                startActivity(intent);
                break;
            case R.id.btnHaushalt:
                intent = new Intent(this, ActivityHaushalt.class);
                startActivity(intent);
                break;
            case R.id.btnMitglied:
                intent = new Intent(this, ActivityMitglied.class);
                startActivity(intent);
                break;
            case R.id.btnWasserzaehler:
                intent = new Intent(this, ActivityWasserzaehler.class);
                startActivity(intent);
                break;
            case R.id.btnWasserstandsmeldung:
                intent = new Intent(this, ActivityWasserstandsmeldung.class);
                startActivity(intent);
                break;
            case R.id.btnVerwaltungspersonal:
                intent = new Intent(this, ActivityVerwaltungspersonal.class);
                startActivity(intent);
                break;

        }
    }
}

/* */
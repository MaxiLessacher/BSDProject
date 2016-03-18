package wasserverwaltung.bsd_map;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import mehdi.sakout.fancybuttons.FancyButton;
import pkgClasses.Database;
import pkgClasses.Haushalt;

public class PersonAdd extends AppCompatActivity implements View.OnClickListener {
    EditText textFieldName, textFieldStrasse, textFieldPlz, textFieldHausnummer;
    FancyButton btnPic, btnAdd;
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Database database = null;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        database = Database.getInstance();
        getAllViews();
    }

    private void getAllViews() {
        textFieldName = (EditText) this.findViewById(R.id.txtName);
        textFieldStrasse = (EditText) this.findViewById(R.id.txtStrasse);
        textFieldPlz = (EditText) this.findViewById(R.id.txtPlz);
        textFieldHausnummer = (EditText) this.findViewById(R.id.txtHausnummer);
        btnAdd = (FancyButton) this.findViewById(R.id.btnAdd);
        btnPic = (FancyButton) this.findViewById(R.id.btnPicChooser);
        imageView = (ImageView) this.findViewById(R.id.imageView);

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        btnAdd.setOnClickListener(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmap = BitmapFactory.decodeFile(picturePath);
            bitmap = Bitmap.createScaledBitmap(
                    bitmap, 150, 150, false);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

    }

    @Override
    public void onClick(View v) {
        boolean add = false;
        if (v.getId()==R.id.btnAdd) {
            if (textFieldName.getText().length() > 0 && textFieldStrasse.getText().length() > 0 && textFieldPlz.getText().length() > 0 && textFieldHausnummer.getText().length() > 0 && imageView.getDrawable() != null) {
                database.addHaushalt(new Haushalt(textFieldName.getText().toString(), Integer.parseInt(textFieldPlz.getText().toString()), textFieldStrasse.getText().toString(), Integer.parseInt(textFieldHausnummer.getText().toString()), bitmap));
                add = true;
            }
            //getText.equals("") didn't work
            else if (textFieldName.getText().length() == 0)
                Toast.makeText(getBaseContext(), "Bitte geben Sie einen Namen ein!", Toast.LENGTH_LONG).show();
            else if (textFieldPlz.getText().length() == 0)
                Toast.makeText(getBaseContext(), "Bitte geben Sie eine Postleitzahl ein!", Toast.LENGTH_LONG).show();
            else if (textFieldStrasse.getText().length() == 0)
                Toast.makeText(getBaseContext(), "Bitte geben Sie eine Strasse ein!", Toast.LENGTH_LONG).show();
            else if (textFieldHausnummer.getText().length() == 0)
                Toast.makeText(getBaseContext(), "Bitte geben Sie eine Hausnummer ein!", Toast.LENGTH_LONG).show();
            else if (imageView.getDrawable() == null)
                Toast.makeText(getBaseContext(), "Bitte waehlen Sie ein Foto aus Ihrer Galerie aus!", Toast.LENGTH_LONG).show();
        }
        if(add)
        this.finish();
    }
}

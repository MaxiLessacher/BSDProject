package wasserverwaltung.bsd_map;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pkgClasses.Database;
import pkgClasses.Haushalt;

public class PersonAdd extends AppCompatActivity implements View.OnClickListener {
    EditText txt1, txt2, txt3, txt4;
    Button btnPic, btnAdd;
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Database database = null;
    Bitmap bitmap;

    private String selectedImagePath;
    //ADDED
    private String filemanagerstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        database = Database.getInstance();
        getAllViews();
    }

    private void getAllViews() {
        txt1 = (EditText) this.findViewById(R.id.txt1);
        txt2 = (EditText) this.findViewById(R.id.txt2);
        txt3 = (EditText) this.findViewById(R.id.txt3);
        txt4 = (EditText) this.findViewById(R.id.txt4);
        btnAdd = (Button) this.findViewById(R.id.btnAdd);
        btnPic = (Button) this.findViewById(R.id.btnPicChooser);
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

    //UPDATED!
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null)
        {
            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnAdd) {
            database.addHaushalt(new Haushalt(txt1.getText().toString(), Integer.parseInt(txt2.getText().toString()), txt3.getText().toString(), Integer.parseInt(txt4.getText().toString()), bitmap));
        }
        this.finish();
    }
}

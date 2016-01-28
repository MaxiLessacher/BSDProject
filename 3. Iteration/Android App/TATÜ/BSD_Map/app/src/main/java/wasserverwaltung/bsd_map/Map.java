package wasserverwaltung.bsd_map;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Address;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import pkgClasses.Database;
import pkgClasses.Haushalt;

public class Map extends FragmentActivity {

    Geocoder geocoder;
    private GoogleMap mMap;
    List<Address> addresses = null;
    Vector<Marker> vMarkers = new Vector<Marker>();
    static final float COORDINATE_OFFSET = 0.00002f;
    float currentZoom = -1;
    Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        db = Database.getInstance();
        //setUpMap();
       //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43, 13)));
       // mMap.moveCamera(CameraUpdateFactory.zoomBy(10));
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        final Button button = (Button) findViewById(R.id.bttnAdd);

         button.setOnClickListener(new View.OnClickListener(){
        //On click function
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PersonAdd.class);
            startActivity(intent);
        }});
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        Vector<Haushalt> vHaushalt = db.getVecHaushalt();
        for(int i = 0; i < vHaushalt.size(); i++){
            createMarker(vHaushalt.get(i));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        geocoder = new Geocoder(getBaseContext());
        if(Geocoder.isPresent()){
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43,13)));
            mMap.moveCamera(CameraUpdateFactory.zoomBy(10));
        }
    }



    private boolean mapAlreadyHasMarkerForLocation(String location) {
        return (vMarkers.contains(location));
    }



    private void createMarker(Haushalt h){
        try{
            addresses = geocoder.getFromLocationName(h.getStrasse() + " " + h.getHausnummer() + ", " + h.getPlz(), 1);
            Address addr = addresses.get(addresses.size() - 1);
            double latitude = addr.getLatitude();
            double longitude = addr.getLongitude();
            if(mapAlreadyHasMarkerForLocation(h.getStrasse() + ", " + h.getPlz())){
                latitude = latitude + COORDINATE_OFFSET;
                longitude = longitude + COORDINATE_OFFSET;
            }
            String snippet = "Strasse: " + h.getStrasse() +
                    "\nPlz: " + h.getPlz();
            LatLng latlng = new LatLng(latitude,longitude);
            vMarkers.add(mMap.addMarker(new MarkerOptions().position(latlng).title(h.getStrasse() + " " + h.getHausnummer() + ", " + h.getPlz()).icon(BitmapDescriptorFactory.fromBitmap(h.getPic()))));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
            connectMarkers();
        }
        catch (IOException e){

        }
    }

    private void connectMarkers(){
        for(int i = 0; i < vMarkers.size(); i++){
            for(int j = 0; j < vMarkers.size(); j++){
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(vMarkers.get(i).getPosition(), vMarkers.get(j).getPosition())
                        .width(5)
                        .color(Color.BLUE));
            }
        }
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(vMarkers.get(0).getPosition(), vMarkers.get(vMarkers.size()-1).getPosition())
                .width(5)
                .color(Color.BLUE));
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}

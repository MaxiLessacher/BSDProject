package wasserverwaltung.bsd_map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import mehdi.sakout.fancybuttons.FancyButton;
import pkgClasses.Database;
import pkgClasses.Haushalt;

public class Map extends FragmentActivity {

    Geocoder geocoder;
    private GoogleMap mMap;
    List<Address> addresses = null;
    Vector<Marker> vMarkers = new Vector<>();
    static final float COORDINATE_OFFSET = 0.00002f;
    Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        db = Database.getInstance();
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        final FancyButton button = (FancyButton) findViewById(R.id.bttnAdd);

         button.setOnClickListener(new View.OnClickListener(){
        //On click function
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PersonAdd.class);
            startActivity(intent);
        }});
        mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

            private final View window = getLayoutInflater().inflate(R.layout.custommarker, null);

            @Override
            public View getInfoWindow(Marker pMarker) {
                TextView lblName = ((TextView) window.findViewById(R.id.lblName));
                TextView lblStrasse = ((TextView) window.findViewById(R.id.lblStrasse));
                TextView lblHausnummer = ((TextView) window.findViewById(R.id.lblHausnummer));
                TextView lblPLZ = ((TextView) window.findViewById(R.id.lblPLZ));
                String snippet = pMarker.getSnippet();
                StringTokenizer tokenSnippet = new StringTokenizer(snippet, ",");
                lblStrasse.setText(tokenSnippet.nextToken());
                lblPLZ.setText(tokenSnippet.nextToken());
                lblName.setText(tokenSnippet.nextToken());
                lblHausnummer.setText(tokenSnippet.nextToken());
                return window;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        createMarker(new Haushalt("Gawo", 9500, "Tschinowitscher Weg", 7, resizeMapIcons("testperson2", 50, 50)));
        createMarker(new Haushalt("Gawo2", 9500, "Lederergasse",27,resizeMapIcons("testperson3",50,50)));
        createMarker(new Haushalt("Gawo3", 9500, "Ossiacherzeile",45,resizeMapIcons("testperson4",50,50)));
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
            String snippet = "" + h.getStrasse() + "," + h.getPlz() + "," + h.getName() + "," + h.getHausnummer();
            LatLng latlng = new LatLng(latitude,longitude);
            vMarkers.add(mMap.addMarker(new MarkerOptions().position(latlng).title(h.getStrasse() + " " + h.getHausnummer() + ", " + h.getPlz()).snippet(snippet).icon(BitmapDescriptorFactory.fromBitmap(h.getPic()))));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
            connectMarkers();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void connectMarkers(){
        for(int i = 0; i < vMarkers.size(); i++){
            for(int j = 0; j < vMarkers.size(); j++){
                mMap.addPolyline(new PolylineOptions()
                        .add(vMarkers.get(i).getPosition(), vMarkers.get(j).getPosition())
                        .width(5)
                        .color(Color.BLUE));
            }
        }
        mMap.addPolyline(new PolylineOptions()
                .add(vMarkers.get(0).getPosition(), vMarkers.get(vMarkers.size() - 1).getPosition())
                .width(5)
                .color(Color.BLUE));
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}

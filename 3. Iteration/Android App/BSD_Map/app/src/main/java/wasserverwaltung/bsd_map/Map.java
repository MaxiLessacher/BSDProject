package wasserverwaltung.bsd_map;

import android.app.Activity;
import android.app.FragmentManager;
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

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Map extends FragmentActivity {

    /*class MyInfoWindowAdapter implements InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }*/

    Geocoder geocoder;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    List<Address> addresses = null;
    Vector<Marker> vMarkers = new Vector<Marker>();
    static final float COORDINATE_OFFSET = 0.00002f;
    float currentZoom = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        /*mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (cameraPosition.zoom != currentZoom){
                    currentZoom = cameraPosition.zoom;
                    if(currentZoom <= 5){
                        for(int i = 0; i <= vMarkers.size(); i++){
                            vMarkers.get(i).ic
                        }
                    }
                }
            }
        }); weat net funken, weil man vom marker ka .getIcon kriagn konn.*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        geocoder = new Geocoder(getBaseContext());
        if(Geocoder.isPresent()){
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43,13)));
            mMap.moveCamera(CameraUpdateFactory.zoomBy(10));
            createMarker(new Haushalt(1,"Tschinowitscher Weg", 9500,  5, 1, 500, true, true), "testperson1");
            createMarker(new Haushalt(1,"Tschinowitscher Weg", 9500,  8, 1, 500, true, true), "testperson2");
            createMarker(new Haushalt(1,"Burgenlandstrasse", 9500,  43, 1, 500, true, true), "testperson3");
            createMarker(new Haushalt(1,"Lederergasse", 9500,  12, 1, 500, true, true), "testperson4");
            connectMarkers();
        }
    }

    private boolean mapAlreadyHasMarkerForLocation(String location) {
        return (vMarkers.contains(location));
    }



    private void createMarker(Haushalt h, String pPictureName){
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
            vMarkers.add(mMap.addMarker(new MarkerOptions().position(latlng).title(h.getStrasse() + " " + h.getHausnummer() + ", " + h.getPlz()).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(pPictureName,130,130))).snippet(snippet)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
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

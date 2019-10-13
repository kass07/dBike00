package com.bycode.dbike.Activity;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.bycode.dbike.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int contador = 0;
    final ArrayList<LatLng> rota = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        // Add a marker in Sydney and move the camera
        final LatLng unileao = new LatLng(-7.225446, -39.328361);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String titulo;
                final LatLng firstPoint;
                final LatLng lastPoint;


                PolylineOptions polylineOptions = new PolylineOptions();

                titulo = String.valueOf(rota.size());
                mMap.addMarker(new MarkerOptions().position(latLng).title(titulo).icon(BitmapDescriptorFactory.defaultMarker()));
                rota.add(latLng);

                if (contador!=0){
                    firstPoint = rota.get(contador-1);
                    lastPoint = rota.get(contador);

                    polylineOptions.add(firstPoint);
                    polylineOptions.add(lastPoint);
                    polylineOptions.color(Color.argb(200,0,0,0));
                    polylineOptions.width(10);
                    mMap.addPolyline(polylineOptions);

                    Toast.makeText(getApplicationContext(),"Passou aki",Toast.LENGTH_SHORT).show();
                }
                contador++;
            }
        });

        // -7.225446,-39.328361
        mMap.addMarker(new MarkerOptions().position(unileao).title("Unileão")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bicycle)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unileao,17));
    }
}

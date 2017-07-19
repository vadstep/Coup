package com.example.vadym.coupbatterystatusapp.UI;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vadym.coupbatterystatusapp.Models.Areas;
import com.example.vadym.coupbatterystatusapp.Models.Scooter;
import com.example.vadym.coupbatterystatusapp.Models.Scooters;
import com.example.vadym.coupbatterystatusapp.R;
import com.example.vadym.coupbatterystatusapp.Utils.AppConst;
import com.example.vadym.coupbatterystatusapp.Utils.PreferenceUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Gson gson;
    private Scooters mScooters;
    private Areas mAreas;
    private boolean hasError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hasError= false;
        setContentView(R.layout.activity_maps);
        gson= new Gson();
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
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.52,13.4050),11f));//Berlin TODO use zoom and location according scooters position
        tryToDrawBikes();
        tryToDrawPolygons();
        if(hasError){
            Toast.makeText(this, "Not all data may be displayed", Toast.LENGTH_SHORT).show();//TODO put text to strings.xml
        }
    }


    /**
     * this function tries to draw bikes on map
     */
    private void tryToDrawBikes(){
        if(PreferenceUtils.loadString(this, AppConst.SCOOTERS_PREF)!=null){
            mScooters=gson.fromJson(PreferenceUtils.loadString(this, AppConst.SCOOTERS_PREF), Scooters.class);
            if(mScooters!=null&&mScooters.getData()!=null&&mScooters.getData().getScooters()!=null) {
                for (int i = 0; i < mScooters.getData().getScooters().size(); i++) {
                    try {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(mScooters.getData().getScooters().get(i).getLocation().getLat(), mScooters.getData().getScooters().get(i).getLocation().getLng())).icon(BitmapDescriptorFactory.defaultMarker(markerColorUtil(mScooters.getData().getScooters().get(i).getEnergyLevel()))));
                    }catch (Exception e){
                        e.printStackTrace();
                        hasError=true;
                    }
                }
            }else{
                Toast.makeText(this, "No scooters to display", Toast.LENGTH_SHORT).show();//TODO put text to strings.xml
            }
        }
    }

    /**
     * this function tries to draw business areas on the map
     */
    private void tryToDrawPolygons(){// TODO reduce On3 complexity in further version
        if(PreferenceUtils.loadString(this, AppConst.SCOOTERS_PREF)!=null){
            mAreas=gson.fromJson(PreferenceUtils.loadString(this, AppConst.AREAS_PREF), Areas.class);
            if(mAreas!=null&&mAreas.getData()!=null&&mAreas.getData().getBusinessAreas()!=null) {
                for (int i = 0; i < mAreas.getData().getBusinessAreas().size(); i++) {
                    try {
                        for (int j = 0; j <  mAreas.getData().getBusinessAreas().get(i).getPolygon().size(); j++) {
                            LatLng [] a = new LatLng[mAreas.getData().getBusinessAreas().get(i).getPolygon().get(j).size()];
                            for (int k = 0; k < mAreas.getData().getBusinessAreas().get(i).getPolygon().get(j).size(); k++) {
                               a[k]=new LatLng(mAreas.getData().getBusinessAreas().get(i).getPolygon().get(j).get(k).get(1),mAreas.getData().getBusinessAreas().get(i).getPolygon().get(j).get(k).get(0));
                            }
                            mMap.addPolygon(new PolygonOptions()
                                    .add(a)
                                    .strokeColor(Color.MAGENTA)
                                    .fillColor(Color.TRANSPARENT));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        hasError=true;
                    }
                }
            }else{
                Toast.makeText(this, "No business areas to display", Toast.LENGTH_SHORT).show();//TODO put text to strings.xml
            }
        }
    }

    /**
     * returns color depends on battery value
     * @param energy battery value
     * @return float color value for BitmapDescriptorFactory
     */
    private float markerColorUtil(Integer energy){
        if (energy >= 51) {
            return BitmapDescriptorFactory.HUE_GREEN;
        } else if (energy < 51 && energy >= 31) {
            return BitmapDescriptorFactory.HUE_YELLOW;
        } else {
            return BitmapDescriptorFactory.HUE_RED;
        }
    }
}

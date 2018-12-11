package nursing.emittoz.com.sample_wms_service.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import nursing.emittoz.com.sample_wms_service.R;
import nursing.emittoz.com.sample_wms_service.WMS.TileProviderFactory;
import nursing.emittoz.com.sample_wms_service.utils.ApplicationConstant;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        LatLng uman = new LatLng(18.5556992,73.8238464);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uman, 14.0f));

    }
/**
 * In this function You call WMS Services You Provide Layer Name and URL
 *
*/
    private void Maplayers(String BASE_URL, String LAYER) {
        TileProvider wmsTileProvider = TileProviderFactory.getOsgeoWmsTileProvider(LAYER, BASE_URL);
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(wmsTileProvider));

    }

    public void wmsService(View view) {
        Toast.makeText(this, "Wms Service", Toast.LENGTH_SHORT).show();
        Maplayers(ApplicationConstant.URL, ApplicationConstant.LAYER_NAME);

    }
}

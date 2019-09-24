package com.example.optimuswayfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    public static final String WAYPOINTS_EXTRA = "com.example.optimuswayfinal.WAYPOINTS_EXTRA";

    private final GeoPoint concas = new GeoPoint(-27.2328968,-52.0276853);
    private final GeoPoint seara = new GeoPoint(-27.153100, -52.310387);
    private final GeoPoint itah = new GeoPoint(-27.276689, -52.339888);
    private final GeoPoint xavantina = new GeoPoint(-27.070874, -52.344416);
    private final GeoPoint arvore = new GeoPoint(-27.075903, -52.455530);
    private final GeoPoint chape = new GeoPoint(-27.102082, -52.620079);

    Integer contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();


        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        MapView map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(-27.202291, -52.070019);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(startPoint);

        RoadManager roadManager = new OSRMRoadManager(this);

        final ArrayList<GeoPoint> waypoints = (ArrayList<GeoPoint>) intent.getSerializableExtra(WAYPOINTS_EXTRA);
        Log.i("MapActivity", "" + waypoints.size());

        RouterTask router = new RouterTask(this, new OnAsyncTaskResult<Road>() {
            @Override
            public void onSuccess(Road road) {
                MapView map = findViewById(R.id.map);
                map.getOverlays().clear();
                Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
                map.getOverlays().add(roadOverlay);
                map.zoomToBoundingBox(BoundingBox.fromGeoPoints(road.getRouteLow()), true, 50, 22.0, 2000L);


                for (GeoPoint waypoint : waypoints) {
                    Marker marker = new Marker(map);
                    marker.setPosition(waypoint);
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    map.getOverlays().add(marker);
                }

                map.invalidate();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        router.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, roadManager, waypoints);
    }

}

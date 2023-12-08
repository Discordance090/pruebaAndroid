package com.Holass.chuteapp;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapview = findViewById(R.id.mapView);
        mapview.setTileSource(TileSourceFactory.MAPNIK);
        mapview.setBuiltInZoomControls(true);
        mapview.setMultiTouchControls(true);
        List<Map<String, Object>> direcciones = new ArrayList<>();

        // Agrega las direcciones a la lista
        Map<String, Object> direccion1 = new HashMap<>();
        direccion1.put("latitud", -33.4489);
        direccion1.put("longitud", -70.6693);
        direccion1.put("titulo", "Santiago, Chile");
        direccion1.put("descripcion", "El mejor país de Chile");
        direcciones.add(direccion1);

        Map<String, Object> direccion2 = new HashMap<>();
        direccion2.put("latitud", -33.4989778);
        direccion2.put("longitud", -70.6180072);
        direccion2.put("titulo", "San Joaquín, Chile");
        direccion2.put("descripcion", "Santo Tomás, Tú puedes");
        direcciones.add(direccion2);


        for (Map<String, Object> direccion : direcciones) {
            double latitud = (double) direccion.get("latitud");
            double longitud = (double) direccion.get("longitud");
            String titulo = (String) direccion.get("titulo");
            String descripcion = (String) direccion.get("descripcion");

            GeoPoint direccionPoint = new GeoPoint(latitud, longitud);

            Marker direccionMarker = new Marker(mapview);
            direccionMarker.setPosition(direccionPoint);
            direccionMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            direccionMarker.setTitle(titulo);
            direccionMarker.setSnippet(descripcion);

            // Agrega el marcador al mapa
            mapview.getOverlays().add(direccionMarker);
        }
        GeoPoint direccionPoint = new GeoPoint(-33.4989778, -70.6180072);


        //Centrar mapa
        IMapController mapController = mapview.getController();
        mapController.setCenter(direccionPoint);
        mapController.setZoom(14);

    }


}

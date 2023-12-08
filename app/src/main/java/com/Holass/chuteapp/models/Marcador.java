package com.Holass.chuteapp.models;

import androidx.annotation.Nullable;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class Marcador {
    MapView mapView;
    Marker marker;
    GeoPoint point;
    String title;
    String snippet;

    public Marcador(MapView mapView, double latitude, double longitude, String title, @Nullable String snippet) {
        point = new GeoPoint(latitude, longitude);
        this.title = title;
        this.snippet = snippet;
        marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(this.title);
        marker.setSnippet(this.snippet);
    }

    public void showMarker(){
        mapView.getOverlays().add(marker);
    }

    public void hideMarker(){
        mapView.getOverlays().clear();
    }
}

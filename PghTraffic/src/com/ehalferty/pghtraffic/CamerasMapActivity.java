package com.ehalferty.pghtraffic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ehalferty.pghtraffic.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class CamerasMapActivity extends MapActivity {
	
	private List<Point2D> coords = new ArrayList<Point2D>();
	private HashMap<Point2D, String> ids = new HashMap<Point2D, String>();
	MapView mapView;
	MapController mapController;
	List<Overlay> mapOverlays;
	Drawable webcamDrawable;
	WebcamIconsOverlay itemizedOverlay;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_map_layout);
        
        SetupMapView();
        
        try {
			LoadCamerasInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AddCameraPinsOverlay();
    }
	
	private void LoadCamerasInfo() throws IOException {
		RawResourceFileLoader rawResourceFileLoader = new RawResourceFileLoader(getResources());
		List<String> lines = rawResourceFileLoader.LoadFile(R.raw.coords_to_ids);
		for (String line: lines) {
			String[] split = line.split(",");
			Point2D point = new Point2D(Float.parseFloat(split[0]), Float.parseFloat(split[1]));
			String id = split[2];
			coords.add(point);
			ids.put(point, id);
		}
	}
	
	private void SetupMapView() {
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setCenter(new GeoPoint(40436528, -80015401));
        mapController.setZoom(15);
	}
	
	private void AddCameraPinsOverlay() {
        mapOverlays = mapView.getOverlays();
        webcamDrawable = this.getResources().getDrawable(R.drawable.webcam);
        Bitmap bitmap = ((BitmapDrawable) webcamDrawable).getBitmap();
        webcamDrawable = new BitmapDrawable(Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        itemizedOverlay = new WebcamIconsOverlay(webcamDrawable, this);
        
        for(Point2D point: coords) {
        	GeoPoint adjusted_point = new GeoPoint((int) (point.X() * 1E6), (int) (point.Y() * 1E6));
        	OverlayItem overlayItem = new OverlayItem(adjusted_point, "", ids.get(point));
        	itemizedOverlay.addOverlay(overlayItem);
        }
        mapOverlays.add(itemizedOverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}

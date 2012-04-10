package com.ehalferty.pghtraffic;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class WebcamIconsOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
	private Context context;

	public WebcamIconsOverlay(Drawable defaultMarker, CamerasMapActivity camerasMapActivity) {
		super(boundCenterBottom(defaultMarker));
		this.context = camerasMapActivity;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays.get(i);
	}

	@Override
	public int size() {
		return overlays.size();
	}
	
	@Override
	 protected boolean onTap(int index)
	 {
		Intent cameraViewIntent = new Intent(context, CameraViewActivity.class);
		OverlayItem item = overlays.get(index);
		cameraViewIntent.putExtra("id", item.getSnippet());
		context.startActivity(cameraViewIntent);
		return true;
	 }

	public void addOverlay(OverlayItem overlayItem) {
		overlays.add(overlayItem);
		populate();
	}

}

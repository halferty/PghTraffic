package com.ehalferty.pghtraffic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.parabolicminds.pittsburghtrafficcameras.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;

public class CamerasMapActivity extends Activity {
	
	private List<Pair<String, Point2D>> id_and_coords = new ArrayList<Pair<String, Point2D>>();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_map_layout);
        
        try {
			LoadCameraInfo("coords_to_ids.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void LoadCameraInfo(String fileName) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = inFile.readLine()) != null) {
			String[] splitLine = line.split(",");
			Point2D pt = new Point2D(Float.parseFloat(splitLine[0]), Float.parseFloat(splitLine[1]));
			id_and_coords.add(new Pair<String, Point2D>(splitLine[2], pt));
		}
	}
}

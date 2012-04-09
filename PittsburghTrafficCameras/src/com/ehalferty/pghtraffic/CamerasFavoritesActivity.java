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

public class CamerasFavoritesActivity extends Activity {
	
	private List<Pair<String, String>> name_and_id = new ArrayList<Pair<String, String>>();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_favorites_layout);
        try {
			LoadCameraInfo("ids_to_names.csv");
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
			name_and_id.add(new Pair<String, String>(splitLine[1], splitLine[0]));
		}
	}
	
}

package com.ehalferty.pghtraffic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;

public class CamerasFavoritesActivity extends Activity {
	
	private List<Pair<String, String>> name_and_id = new ArrayList<Pair<String, String>>();
	ArrayList<String> itemList = new ArrayList<String>();
	
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
	
	// Load favorites list from a file.
	@SuppressWarnings("unchecked")
	private ArrayList<String> LoadData(String filename) {
		ArrayList<String> al = new ArrayList<String>();
		InputStream is;
		try {
			is = openFileInput(filename);
			ObjectInputStream ois = new ObjectInputStream(is);
			al = (ArrayList<String>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
}

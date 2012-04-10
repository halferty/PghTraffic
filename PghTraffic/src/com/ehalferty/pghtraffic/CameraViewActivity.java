package com.ehalferty.pghtraffic;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CameraViewActivity extends Activity {

	private ArrayList<String> names = new ArrayList<String>();
	private HashMap<String, String> ids = new HashMap<String, String>();
	private ArrayAdapter<String> adapter;
	protected ListView camerasList;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_view_layout);
    }

	// Add a new camera to favorites file.
	private void AddFavorite(String filename, ArrayList<String> data) {
		try {
			ArrayList<String> al = new ArrayList<String>();
			InputStream is;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(baos);
			out.writeObject(al);
			FileOutputStream fo = openFileOutput(filename, Context.MODE_PRIVATE);
			fo.write(baos.toByteArray());
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

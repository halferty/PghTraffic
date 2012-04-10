package com.ehalferty.pghtraffic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CamerasListActivity extends Activity implements OnItemClickListener {

	private ArrayList<String> names = new ArrayList<String>();
	private HashMap<String, String> ids = new HashMap<String, String>();
	private ArrayAdapter<String> adapter;
	protected ListView camerasList;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_list_layout);
        try {
			LoadCameraInfo();
			camerasList = (ListView) this.findViewById(R.id.listView1);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
	        camerasList.setAdapter(adapter);
	        camerasList.setOnItemClickListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        for (String name: names) {
            Log.d("lol", name);
        }
    }
	
	private void LoadCameraInfo() throws IOException {
		InputStream is = getResources().openRawResource(R.raw.ids_to_names);
		String[] lines = ReadInputStream(is).split("\\r?\\n");
		for (String line: lines) {
			String[] split = line.split(",");
			String id = split[0];
			String name = split[1];
			names.add(name);
			ids.put(name, id);
		}
	}
	
	public static String ReadInputStream(InputStream in) throws IOException {
		StringBuffer stream = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			stream.append(new String(b, 0, n));
		}
		return stream.toString();
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		// Here we would send the name and id of the camera to CameraViewActivity
		Log.d("lol", "name=" + arg3);
		Log.d("lol", "id=" + ids.get(arg3));
		
	}
}

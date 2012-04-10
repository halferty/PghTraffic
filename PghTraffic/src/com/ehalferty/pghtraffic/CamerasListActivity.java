package com.ehalferty.pghtraffic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CamerasListActivity extends Activity implements OnItemClickListener {

	private ArrayList<String> names = new ArrayList<String>();
	private HashMap<String, String> ids = new HashMap<String, String>();
	private ArrayAdapter<String> adapter;
	protected ListView camerasList;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_list_layout);
        try {
			LoadCamerasInfo();
			camerasList = (ListView) this.findViewById(R.id.listView1);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
	        camerasList.setAdapter(adapter);
	        camerasList.setOnItemClickListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void LoadCamerasInfo() throws IOException {
		RawResourceFileLoader rawResourceFileLoader = new RawResourceFileLoader(getResources());
		List<String> lines = rawResourceFileLoader.LoadFile(R.raw.ids_to_names);
		for (String line: lines) {
			String[] split = line.split(",");
			String id = split[0];
			String name = split[1];
			names.add(name);
			ids.put(name, id);
		}
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		// Send the name and id of the camera to CameraViewActivity
		String name = ((TextView)arg1).getText().toString();
		String id = ids.get(name);
		
		Intent cameraViewIntent = new Intent("my.ehalferty.pghtraffic.cameraView");
		cameraViewIntent.putExtra("name", name);
		cameraViewIntent.putExtra("id", id);
		this.startActivity(cameraViewIntent);
	}
}

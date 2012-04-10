package com.ehalferty.pghtraffic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CamerasFavoritesActivity extends Activity implements OnItemClickListener {

	public static final String PREF_FILE_NAME = "PrefFile";
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	private List<String> names = new ArrayList<String>();
	private HashMap<String, String> ids = new HashMap<String, String>();
	private ArrayAdapter<String> adapter;
	protected ListView camerasList;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_favorites_layout);
		camerasList = (ListView) this.findViewById(R.id.listViewFavorites);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        camerasList.setAdapter(adapter);
        camerasList.setOnItemClickListener(this);
        registerForContextMenu(camerasList);
        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        LoadFavorites();
    }

	private void LoadFavorites() {
		Map<String, ?> prefsMap = preferences.getAll();
		names.clear();
		ids.clear();
		for (Map.Entry<String, ?> entry: prefsMap.entrySet()) {
		        names.add(entry.getKey());
		        ids.put(entry.getKey(), entry.getValue().toString());
		}
    	adapter.notifyDataSetChanged();
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
	
	// Context menu creator
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

	public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
        case R.id.remove_item:
        	RemoveItem(info.position);
            return true;
        case R.id.cancel:
            return true;
        }
        return false;
    }

	private void RemoveItem(int position) {
    	editor.remove(names.get(position));
    	editor.commit();
		ids.remove(names.get(position));
    	names.remove(position);
    	adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		LoadFavorites();
    	adapter.notifyDataSetChanged();
	}
}

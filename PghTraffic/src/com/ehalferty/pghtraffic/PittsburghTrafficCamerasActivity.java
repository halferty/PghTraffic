package com.ehalferty.pghtraffic;

import com.ehalferty.pghtraffic.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class PittsburghTrafficCamerasActivity extends TabActivity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();

        TabSpec listSpec = tabHost.newTabSpec("List");
        listSpec.setIndicator("List", getResources().getDrawable(R.drawable.ic_tab_list_selected));
        Intent listIntent = new Intent(this, CamerasListActivity.class);
        listSpec.setContent(listIntent);

        TabSpec mapSpec = tabHost.newTabSpec("Map");
        mapSpec.setIndicator("Map", getResources().getDrawable(android.R.drawable.ic_menu_mapmode));
        Intent mapIntent = new Intent(this, CamerasMapActivity.class);
        mapSpec.setContent(mapIntent);

        TabSpec favoritesSpec = tabHost.newTabSpec("Favorites");
        favoritesSpec.setIndicator("Favorites", getResources().getDrawable(android.R.drawable.ic_menu_info_details));
        Intent favoritesIntent = new Intent(this, CamerasMapActivity.class);
        favoritesSpec.setContent(favoritesIntent);

        tabHost.addTab(listSpec);
        tabHost.addTab(mapSpec);
        tabHost.addTab(favoritesSpec);
    }
}
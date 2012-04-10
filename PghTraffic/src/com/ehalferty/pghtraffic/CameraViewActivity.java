package com.ehalferty.pghtraffic;

import com.ehalferty.pghtraffic.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class CameraViewActivity extends Activity {

	public static final String PREF_FILE_NAME = "PrefFile";
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	private WebView webView;
	private String URL;
	private String name, id;
	private Button back, add_to_favorites;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_view_layout);

        // Load the intent extra data
        id = getIntent().getStringExtra("id");
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        } else {
        	name = id;
        }
        
        // Initialize the webview
        URL = "http://www.dot.state.pa.us/Penndot/Districts/district11.nsf/TCID" + id;
        SetupWebView();
        
        // Set the text above the camera to the camera name.
        ((TextView) findViewById(R.id.camera_name)).setText(name);
        
        // Back button
        this.back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        		}
        	});
        
        // Add to Favorites button
        this.add_to_favorites = (Button) findViewById(R.id.add_to_favorites);
        add_to_favorites.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		AddToFavorites();
        	}
        });
        
        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        editor = preferences.edit();
    }
	
	private void AddToFavorites() {
		if (preferences.contains(name)) {
			
		} else {
			editor.putString(name, id);
			editor.commit();
		}
	}
	
	private void SetupWebView() {
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(URL);
	}
}

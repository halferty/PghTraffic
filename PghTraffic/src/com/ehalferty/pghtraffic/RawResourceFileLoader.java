package com.ehalferty.pghtraffic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.res.Resources;

// Loads a text file from an android resource (tested with res/raw)
// and makes it available as an array of strings.

public class RawResourceFileLoader {
	
	Resources resources;
	List<String> file_lines = new ArrayList<String>();
	
	public RawResourceFileLoader(Resources resources) throws IOException {
		this.resources = resources;
	}
	
	public List<String> LoadFile(int resourceID) throws IOException {
		InputStream is = resources.openRawResource(resourceID);
		return file_lines = Arrays.asList(ReadInputStream(is).split("\\r?\\n"));
	}
	
	private String ReadInputStream(InputStream in) throws IOException {
		StringBuffer stream = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			stream.append(new String(b, 0, n));
		}
		return stream.toString();
	}
}

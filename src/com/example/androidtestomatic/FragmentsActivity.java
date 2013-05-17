package com.example.androidtestomatic;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;

public class FragmentsActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	
		// ---get the current display info---
		WindowManager wm = getWindowManager();
		Display d = wm.getDefaultDisplay();
		if (d.getWidth() > d.getHeight()) {
			// ---landscape mode---
			Fragment1 fragment1 = new Fragment1();
			// android.R.id.content refers to the content
			// view of the activity
			fragmentTransaction.replace(android.R.id.content, fragment1);
		} else {
			// ---portrait mode---
			Fragment2 fragment2 = new Fragment2();
			fragmentTransaction.replace(android.R.id.content, fragment2);
		}
		fragmentTransaction.commit();
		
		// setContentView(R.layout.fragments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragments, menu);
		return true;
	}

}

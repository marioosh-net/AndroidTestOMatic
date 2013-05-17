package com.example.androidtestomatic;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		
		/**
		 * get passed data
		 */
		String passedString = getIntent().getStringExtra("text");
		Toast.makeText(this,passedString, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

	public void ok(View view) {
		Intent i = new Intent();
		TextView tv = (TextView) findViewById(R.id.thirdText);
		i.setData(Uri.parse(tv.getText().toString()));
		
		setResult(RESULT_OK, i);
		
		//---closes the activity---
		finish();
	}
}

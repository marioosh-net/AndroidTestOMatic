package com.example.androidtestomatic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.androidtestomatic.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
        EditText editText = (EditText) findViewById(R.id.text1);
        Log.d("",editText.toString());
        editText.addTextChangedListener(new TextValidator(editText));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void click(View view) {
    	Log.d("button","click");
    	EditText editText = (EditText) findViewById(R.id.text1);
    	String message = editText.getText().toString();
    	if(message.length() < 1) {
    		editText.setError("Can't be empty!");
    		return;
    	}
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void clear(View view) {
    	((EditText)findViewById(R.id.text1)).setText("");
    	Log.d("button", "clear");
    }

}


class TextValidator implements TextWatcher {

	private TextView editText;
	
	public TextValidator(TextView editText) {
		this.editText = editText;
	}
	
	@Override
	public void afterTextChanged(Editable s) {
		if(editText.getText().toString().length() > 10) {
			editText.setError("max 10 characters!");
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
	
}
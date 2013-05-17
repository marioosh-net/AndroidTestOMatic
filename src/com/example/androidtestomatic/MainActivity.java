package com.example.androidtestomatic;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.androidtestomatic.MESSAGE";
	String tag = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
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
    	
    	/**
    	 * start another activity
    	 */
    	startActivity(intent);
    }
    
    public void clear(View view) {
    	((EditText)findViewById(R.id.text1)).setText("");
    	Log.d("button", "clear");
    }
    
    public void exit(View view) {
    	finish();
    }
    
    /**
     * some progress dialog
     * @param view
     */
    public void progressDialog(View view) {
		final ProgressDialog progressDialog = new ProgressDialog(this);// ProgressDialog.show(this,	"Doing something", "Please wait...", true);
		
		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setTitle("Downloading files...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
		new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int whichButton) {
				Toast.makeText(getBaseContext(),
						"OK clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
		new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Toast.makeText(getBaseContext(), "Cancel clicked!",
						Toast.LENGTH_SHORT).show();
			}
		});			
		progressDialog.show();
		progressDialog.setProgress(0);
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 15; i++) {
					try {
						Thread.sleep(1000);
						progressDialog.incrementProgressBy((int) (100 / 15));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				progressDialog.dismiss();
			}
		}).start();
    }
    
    /**
     * some alert dialog
     * (using Builder)
     * @param view
     */
    public void alert(View view) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Dialog")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
    }
    
    /**
     * start another activity by its action name
     * @param view
     */
    public void second(View view) {
    	
    	/**
    	 * start activity that can be outside project
    	 * by name
    	 */
    	startActivity(new Intent("com.example.androidtestomatic.SecondActivity"));
    	
    	/**
    	 * start activity in the same project
    	 *
    	startActivity(new Intent(this, SecondActivity.class));
    	*/
    }
    
    int request_Code = 1;
    
    /**
     * start activity that return result
     * @param view
     */
    public void thirdWithResult(View view) {
    	startActivityForResult(new Intent("com.example.androidtestomatic.ThirdActivity"), request_Code);
    }

    // hadle results of started activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == request_Code) {
    		if (resultCode == RESULT_OK) {
    			Toast.makeText(this,data.getData().toString(), Toast.LENGTH_SHORT).show();    			
    		}
    	}
    }

	public void onStart() {
		super.onStart();
		Log.d(tag, "In the onStart() event");
	}

	public void onRestart() {
		super.onRestart();
		Log.d(tag, "In the onRestart() event");
	}

	public void onResume() {
		super.onResume();
		Log.d(tag, "In the onResume() event");
	}

	public void onPause() {
		super.onPause();
		Log.d(tag, "In the onPause() event");
	}

	public void onStop() {
		super.onStop();
		Log.d(tag, "In the onStop() event");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d(tag, "In the onDestroy() event");
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
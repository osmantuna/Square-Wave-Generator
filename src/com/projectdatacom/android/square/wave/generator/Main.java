package com.projectdatacom.android.square.wave.generator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);

		Button btn_ciz = (Button) findViewById(R.id.button1);
		btn_ciz.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				EditText txt_freq = (EditText) findViewById(R.id.editText1);
				EditText txt_amp = (EditText) findViewById(R.id.editText2);
				EditText txt_har = (EditText) findViewById(R.id.editText3);
				CheckBox chk = (CheckBox) findViewById(R.id.checkBox1);

				DisplayMetrics metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);

				int width = metrics.widthPixels;
				int height = metrics.heightPixels;

				Intent i = new Intent(Main.this, Draw.class);
				if (txt_freq.getText().toString().trim().length() > 0) {
					i.putExtra("freq",
							Integer.parseInt(txt_freq.getText().toString()));
				} else {
					i.putExtra("freq", 1);
				}

				if (txt_amp.getText().toString().trim().length() > 0) {
					i.putExtra("amp",
							Integer.parseInt(txt_amp.getText().toString()));
				} else {
					i.putExtra("amp", 11);
				}

				if (txt_har.getText().toString().trim().length() > 0) {
					i.putExtra("har",
							Integer.parseInt(txt_har.getText().toString()));
				} else {
					i.putExtra("har", 7);
				}

				if (chk.isChecked()) {
					i.putExtra("show", true);
				} else {
					i.putExtra("show", false);
				}

				i.putExtra("width", width);
				i.putExtra("height", height);
				startActivity(i);
			}
		});

	}

}

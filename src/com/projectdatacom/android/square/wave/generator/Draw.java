package com.projectdatacom.android.square.wave.generator;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Draw extends Activity {

	public static double PI = 3.14159265358979323846;
	public static int freq = 1;
	public static int amp = 11;
	public static int har = 5;
	public static Boolean show = false;
	public static int width = 400;
	public static int height = 400;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			freq = extras.getInt("freq");
			amp = extras.getInt("amp");
			har = extras.getInt("har");
			show = extras.getBoolean("show");
			width = extras.getInt("width");
			height = extras.getInt("height");
		}
		
		if (har % 2 == 0) {
			har -= 1;
		}

		setContentView(new Drawer(this));
	}

	class Drawer extends View {

		public Drawer(Context context) {
			super(context);
			this.setBackgroundColor(Color.BLACK);
		}

		@Override
		public void onDraw(Canvas canvas) {
			Paint p = new Paint();
			
			double toplam = 0;
			double gecici = 0;

			for (double i = 0.1; i < width; i += 1) {
				for (int j = 1; j < har; j += 2) {
					gecici = 4 * amp / (PI * j) * 10
							* Math.sin(2 * PI * freq * j * i / 360);
					toplam += gecici;

					if (show) {
						p.setColor(Color.GREEN);
						canvas.drawPoint((int) (1 + i), (int) ((height / 2) - gecici), p);
					}

				}
				p.setColor(Color.RED);
				canvas.drawPoint((int) (1 + i), (int) ((height / 2) - toplam), p);
				toplam = 0;
			}
			
			p.setColor(Color.WHITE);
			canvas.drawText("Freq: " + freq + "  #  Amp: " + amp + "  #  Har: " + har, 20, 30, p);
		}

	}

}

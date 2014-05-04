package com.example.singingwithnina;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.media.SoundPool;
import android.media.AudioManager;
import android.view.View.OnClickListener;
import android.os.CountDownTimer;
import android.util.Log;

public class MelodyGenerator extends Activity {

	private Button PlayButton;
	private Button StopButton;
	private SoundPool soundPool;
	private int C1;
	private int D1;
	private int E1;
	private int F1;
	private int G1;
	private int A1;
	private int B1;
	private ArrayList<Double> frequencies = new ArrayList<Double>(); 

	private int streamID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_melody_generator);
		
		PlayButton = (Button) findViewById(R.id.button1);
		StopButton = (Button) findViewById(R.id.button2);
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		C1 = soundPool.load(this, R.raw.c1, 1);
		D1 = soundPool.load(this, R.raw.d1, 1);
		E1 = soundPool.load(this, R.raw.e1, 1);
		F1 = soundPool.load(this, R.raw.f1, 1);
		G1 = soundPool.load(this, R.raw.g1, 1);
		A1 = soundPool.load(this, R.raw.a1, 1);
		B1 = soundPool.load(this, R.raw.b1, 1);

		
		RandomMelody();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.melody_generator, menu);
		return true;
	 
	}
	
	private void RandomMelody() {
		PlayButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 new CountDownTimer(5000, 1000) {

				     public void onTick(long millisUntilFinished) {
							NextTone();
				     }

				     public void onFinish() {
							soundPool.stop(streamID);
							ArrayList<Double> frequency = generateListReadyForGraphing(frequencies); //frequency is the array for graphing
							Log.e("tones", + frequencies.get(0) + " "+ frequencies.get(1) + " "+ frequencies.get(2) + " "+ frequencies.get(3) + " ");
				     }
				  }.start();
				  
			}
			
			
			public void NextTone() {
				Random r = new Random();
				int rnd = r.nextInt(6);
				switch(rnd) {
					case 0: frequencies.add(261.63);
							streamID = soundPool.play(C1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 1: frequencies.add(293.66);
							streamID = soundPool.play(D1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 2: frequencies.add(329.63);
							streamID = soundPool.play(E1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 3: frequencies.add(349.23);
							streamID = soundPool.play(F1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 4: frequencies.add(392.00);
							streamID = soundPool.play(G1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 5: frequencies.add(440.00);
							streamID = soundPool.play(A1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
					case 6: frequencies.add(493.88);
							streamID = soundPool.play(B1, 1.0f, 1.0f, 0, 0, 1.0f);
							break;
				}
			}

		});
		
		
		StopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundPool.stop(streamID);
			}
		});
	}
	private ArrayList<Double> generateListReadyForGraphing(ArrayList<Double> frequency){ 
		ArrayList<Double> output = new ArrayList<Double>();
		for(int i = 0; i < frequency.size(); i++ ){
			if(i == frequency.size() - 1){ // because the last tone is double time
				for(int index = 0; index < 88; index++){
					output.add(frequency.get(i));
				}
			}
			else{
				for(int index = 0; index < 44; index++){
					output.add(frequency.get(i));
				}
			}
		}
		return output;
	}
}

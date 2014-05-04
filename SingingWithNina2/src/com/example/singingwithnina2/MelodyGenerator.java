package com.example.singingwithnina2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


import android.os.Bundle;
import android.app.Activity;

import android.media.SoundPool;
import android.media.AudioManager;

import android.os.CountDownTimer;


public class MelodyGenerator extends Activity {

	private SoundPool soundPool;
	private int C1;
	private int D1;
	private int E1;
	private int F1;
	private int G1;
	private int A1;
	private int B1;
	
	private ArrayList<Integer> generatedMelody;
	private ArrayList<Double> freq;


	private int streamID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		generatedMelody = new ArrayList<Integer>();
		freq = new ArrayList<Double>();

		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

		C1 = soundPool.load(this, R.raw.c1, 1);
		D1 = soundPool.load(this, R.raw.d1, 1);
		E1 = soundPool.load(this, R.raw.e1, 1);
		F1 = soundPool.load(this, R.raw.f1, 1);
		G1 = soundPool.load(this, R.raw.g1, 1);
		A1 = soundPool.load(this, R.raw.a1, 1);
		B1 = soundPool.load(this, R.raw.b1, 1);
		

		
	}
			
			
		public double NextTone() {
			Random r = new Random();
			int rnd = r.nextInt(6);
			switch(rnd) {
				case 0: generatedMelody.add(0);
						streamID = soundPool.play(C1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 261.63;
				case 1: generatedMelody.add(1);
						streamID = soundPool.play(D1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 293.66;
				case 2: generatedMelody.add(2);
						streamID = soundPool.play(E1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 329.63;
				case 3: generatedMelody.add(3);
						streamID = soundPool.play(F1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 349.23;
				case 4: generatedMelody.add(4);
						streamID = soundPool.play(G1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 392.00;
				case 5: generatedMelody.add(5);
						streamID = soundPool.play(A1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 440.00;
				case 6: generatedMelody.add(6);
						streamID = soundPool.play(B1, 1.0f, 1.0f, 0, 0, 1.0f);
						return 493.88;
			}
			return -1;
		}
			
		public void DetNextTone(int toneNumber) {
			switch(toneNumber) {
				case 0: streamID = soundPool.play(C1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 1: streamID = soundPool.play(D1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 2: streamID = soundPool.play(E1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 3: streamID = soundPool.play(F1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 4: streamID = soundPool.play(G1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 5: streamID = soundPool.play(A1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
				case 6: streamID = soundPool.play(B1, 1.0f, 1.0f, 0, 0, 1.0f);
						break;
			}
		}
	
			
	public ArrayList<Double> randomPlay(){
		new CountDownTimer(5000, 1000) {

		     public void onTick(long millisUntilFinished) {
					freq.add(NextTone());
		     }

		     public void onFinish() {
		    	 soundPool.stop(streamID);
		     }
		  }.start();
		  return freq;
	}
	
	public void detPlay(){
		new CountDownTimer(5000, 1000) {
			int counter = 0;

		     public void onTick(long millisUntilFinished) {
					DetNextTone(counter);
					counter++;
		     }

		     public void onFinish() {
		    	 soundPool.stop(streamID);
		     }
		  }.start();
		
	}
	
	public void stop(){
		soundPool.stop(streamID);
	}
	
}

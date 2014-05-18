package com.example.singingwithnina2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends Activity{
	
	private Button start;
	private Button scores;
	private Button info;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		start = (Button) findViewById(R.id.startButton);
		scores= (Button) findViewById(R.id.scoresButton);
		info = (Button) findViewById(R.id.infoButton);
		
		
		
		
		SharedPreferences scoreData = this.getSharedPreferences(
			      "com.example.singingwithnina2", Context.MODE_PRIVATE);
		
	
		
		
}
	
public void onClick(View v){
	
		MediaPlayer button = MediaPlayer.create(this, R.raw.button2); //LINE ONE TO ADDING A BUTTON SOUND!!
				
		switch(v.getId()) {
		default:
			break;
		case R.id.startButton:
			button.start();  //LINE TWO TO ADDING A BUTTON SOUND!!
			Intent intent = new Intent(this, PlaySingActivity.class);
			startActivity(intent);
			break;
			
		case R.id.scoresButton:
			button.start();
			Intent intent2 = new Intent(this, HighScores.class);
			startActivity(intent2);
			break;
			
		case R.id.infoButton:
			button.start();
			Intent intent3 = new Intent(this, Information.class);
			startActivity(intent3);
			break;
		
		
	}
}
}
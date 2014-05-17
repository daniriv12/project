package com.example.singingwithnina2;

import android.app.Activity;
import android.content.Intent;
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
}
	
public void onClick(View v){
				
		switch(v.getId()) {
		default:
			break;
		case R.id.startButton:
			Intent intent = new Intent(this, PlaySingActivity.class);
			startActivity(intent);
			break;
			
		case R.id.scoresButton:
			Intent intent2 = new Intent(this, HighScores.class);
			startActivity(intent2);
			break;
			
		case R.id.infoButton:
			Intent intent3 = new Intent(this, Information.class);
			startActivity(intent3);
			break;
		
		
	}
}
}
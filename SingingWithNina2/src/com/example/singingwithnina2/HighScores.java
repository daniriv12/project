package com.example.singingwithnina2;

import com.example.singingwithnina2.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HighScores extends Activity{
	
	private Button back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		
		back = (Button) findViewById(R.id.backButton);

}
public void onClick(View v){
		
		Intent intent = new Intent(this, Start.class);
		startActivity(intent);
	}
}
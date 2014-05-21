package com.example.singingwithnina2;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.singingwithnina2.MelodyGenerator;
import com.example.singingwithnina2.AudioProcessing;


public class PlaySingActivity extends Activity{
	
	private MelodyGenerator melodyGenerator;
	private ArrayList<Integer> genFreq;

	private AudioProcessing audioProcessor;
	private ArrayList<Integer> realFreq;
	Bundle savedInstanceState;
	
	
	
	
	ImageView iv;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    
	
	protected void onCreate(Bundle savedInstanceState) {
		this.savedInstanceState = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playsing);
		melodyGenerator = new MelodyGenerator(this);
		
		audioProcessor = new AudioProcessing(this, new Handler());
	
		realFreq = new ArrayList<Integer>();
		genFreq = melodyGenerator.Generate();

	
		Button stop = (Button) this.findViewById(R.id.stopButton);
		Button play = (Button) this.findViewById(R.id.playButton);
		Button sing = (Button) this.findViewById(R.id.singButton);
		Button results = (Button) this.findViewById(R.id.resultsButton);
		
		stop.setSoundEffectsEnabled(false);
		play.setSoundEffectsEnabled(false);
		sing.setSoundEffectsEnabled(false);
		results.setSoundEffectsEnabled(false);
		
		iv = (ImageView) this.findViewById(R.id.imageView1);
        bitmap = Bitmap.createBitmap((int) 512, (int) 200,
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(0xff33B5E5);
        paint.setStrokeWidth(2.0f);
        iv.setImageBitmap(bitmap);

	
	}
	public void buttonClicked(View v){
		
		
		
		switch(v.getId()) {
		default:
			break;
		case R.id.playButton:
			melodyGenerator.Play();
			break;
			
		case R.id.singButton:
			audioProcessor.sing();
			break;
			
		case R.id.stopButton:
	
			melodyGenerator.Generate();
			break;
			
		case R.id.resultsButton:
		    finish();
			Intent intent2 = new Intent(this, HighScores.class);
			startActivity(intent2);
			break;
			
		
			
	}
	}
	
	public void imReady(){
		Intent intent = new Intent(this, GrapherTwo.class);
		Bundle data = new Bundle();
		data.putIntegerArrayList("melody", generateListReadyForGraphing(genFreq));
		data.putIntegerArrayList("sing", realFreq);
		intent.putExtras(data);
		finish();
		startActivity(intent);
	
	}
	

    protected void publishProgress(final Integer frequency, final double[]... toTransform) {
    	// realFreq.add(frequency);
    	realFreq.add(frequency);

        canvas.drawColor(Color.BLACK);
        int x = 0;
        for (int i = 0; i < toTransform[0].length; i++) {
            //int downy = (int) (100 - (toTransform[0][i] * 10));
            //int upy = 100;
        	int downy = (int) (100 - (toTransform[0][i] * 10)); 
        	int upy = (int) (100 + (toTransform[0][i] * 10));

            canvas.drawLine(x, downy, x, upy, paint);
            x += 3;
        }

        iv.invalidate();
      
    }
  
    private ArrayList<Integer> generateListReadyForGraphing(ArrayList<Integer> frequency){ 
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0; i < frequency.size(); i++ ){
			if(i == frequency.size() - 1){ // because the last tone is double time heh
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

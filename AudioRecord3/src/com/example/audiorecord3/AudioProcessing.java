package com.example.audiorecord3;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.audiorecord3.RealDoubleFFT;

public class AudioProcessing extends Activity implements OnClickListener{
	
	int frequency = 8000;
    int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    private RealDoubleFFT transformer;
    int blockSize = 256;

    Button startStopButton;
    boolean started = false;

    RecordAudio recordTask;

    ImageView imageView;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    //AudioRecord audioRecord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startStopButton = (Button) this.findViewById(R.id.StartStopButton);
        startStopButton.setOnClickListener(this);

        transformer = new RealDoubleFFT(blockSize);

        imageView = (ImageView) this.findViewById(R.id.ImageView01);
        bitmap = Bitmap.createBitmap((int) 256, (int) 100,
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        imageView.setImageBitmap(bitmap);

    }

    public class RecordAudio extends AsyncTask<Void, double[], Void> {
    	
    	private ArrayList<Double> frequencies;

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                // int bufferSize = AudioRecord.getMinBufferSize(frequency,
                // AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
                int bufferSize = AudioRecord.getMinBufferSize(frequency, 
                        channelConfiguration, audioEncoding); 

                AudioRecord audioRecord = new AudioRecord( 
                        MediaRecorder.AudioSource.MIC, frequency, 
                        channelConfiguration, audioEncoding, bufferSize); 

                short[] buffer = new short[blockSize];
                double[] toTransform = new double[blockSize];

                audioRecord.startRecording();

                // started = true; hopes this should true before calling
                // following while loop
                
                while (started) {
                	
                	
                    int bufferReadResult = audioRecord.read(buffer, 0,
                            blockSize);

                    for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
                        toTransform[i] = (double) buffer[i] / 32768.0; // signed
                                                                        // 16
                    }                                       // bit
                        transformer.ft(toTransform);
                        Log.e("HZ values",  findBiggest(toTransform)  * 8000/toTransform.length + " ");
                        
                        publishProgress(toTransform);
                        
                        frequencies.add((double)(findBiggest(toTransform)  * 8000/toTransform.length));
                        
                        
                        


                }

                audioRecord.stop();
                audioRecord.release();

            } catch (Throwable t) {
                t.printStackTrace();
                Log.e("AudioRecord", "Recording Failed");
            }
            return null;
        }
        
        public ArrayList<Double> getFrequencies(){
        	return frequencies;
        	
        }
        
        @Override
        protected void onProgressUpdate(double[]... toTransform) {

            canvas.drawColor(Color.BLACK);

            for (int i = 0; i < toTransform[0].length; i++) {
                int x = i;
                int downy = (int) (100 - (toTransform[0][i] * 10));
                int upy = 100;

                canvas.drawLine(x, downy, x, upy, paint);
            }

            imageView.invalidate();

            // Auto-generated method stub
            // super.onProgressUpdate(values);
        }

    }

    public int findBiggest(double[] toTransform){
    	double biggest = 0;
    	int biggestIndex = 0;
    	
    
    	for (int index = 0; index < toTransform.length ; index++){
    		if (toTransform[index] > biggest){
    			biggest = toTransform[index];
    			biggestIndex = index;
    		}
    		
    		
    	}
    	
    	return biggestIndex;
    }
    
//    
//    public void onClick(View arg0 ){
//    	
//    	startStopButton.setText("Wait");
//    	recordTask = new RecordAudio();
//    	recordTask.execute();
//    	
//    	new CountDownTimer(5000, 1000) {
//    		
//    		public void onTick(long millisUntilFinished){
//    			startStopButton.setText("" + millisUntilFinished);
//    		}
//    		
//    		public void onFinish(){
//    			recordTask.cancel(true);
//    		}
//    	
//    }.start();
//    	
//    }
//    
    
    public void onClick(View arg0) {
    	

        if (started) {
            started = false;
            
            startStopButton.setText("Start");
            recordTask.cancel(true);
        } else {
        	}
            started = true;
            startStopButton.setText("Sing!");
            recordTask = new RecordAudio();
            recordTask.execute();
            new CountDownTimer (5000, 1000){
            	
            	public void onTick(long millisUntilFinished){
          
            	}
            	
            	public void onFinish(){
            		started = false;
            		startStopButton.setText("Start");
            				
            		recordTask.cancel(true);
            	}
            
            }.start();
        }
    }

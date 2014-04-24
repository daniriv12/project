package com.example.audiorecordingtry;


import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
	Button start, stop, play, stopplay;
	private MediaPlayer mediaPlayer;
	private MediaRecorder recorder;
	private String outputFile;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		outputFile = Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
		
		start = (Button) this.findViewById(R.id.startr);
		stop = (Button) this.findViewById(R.id.stopr);
		play = (Button) this.findViewById(R.id.playr);
		stopplay = (Button) this.findViewById(R.id.stopp);
	}
		
		
		public void buttonTapped(View v){
			switch(v.getId()) {
			case R.id.playr:
				try{
					playRecording();
				}catch (Exception e){
					e.printStackTrace();
				}
				break;
			case R.id.stopr:
				try{
					stopRecording();
				}catch (Exception e){
					e.printStackTrace();
				}
				break;
			case R.id.startr:
				try{
					startRecording();
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case R.id.stopp:
				try{
					stopPlayback();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}


		private void stopPlayback() {
			if(mediaPlayer != null){
				mediaPlayer.stop();
			}
			
		}


		private void playRecording() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
			ditchMediaPlayer();
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(outputFile);
			mediaPlayer.prepare();
			mediaPlayer.start();
			
		}


		private void ditchMediaPlayer() {
			if(mediaPlayer != null){
				try{
					mediaPlayer.release();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			
		}


		private void stopRecording() {
			if(recorder != null){
				recorder.stop();
			}
		}


		private void startRecording() throws Exception{
			ditchMediaRecorder();
			File outFile = new File(outputFile);
			
			if(outFile.exists()){
				outFile.delete();
			}
			recorder = new MediaRecorder();
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
			recorder.setOutputFile(outputFile);
			recorder.prepare();
			recorder.start();
						
		}


		private void ditchMediaRecorder() {
			if(recorder != null){
				recorder.release();
			}
			}
			
}

		

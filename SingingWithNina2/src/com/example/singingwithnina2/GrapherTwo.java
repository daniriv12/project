package com.example.singingwithnina2;

import android.app.Activity;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;

//THIS CLASS IS JUST AN ATTEMPT TO FIX CLASS GRAPHER, PAY IT NO MIND

public class GrapherTwo extends Activity{
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grapher);
		
		
		ArrayList<Integer> melodyData;
		ArrayList<Integer> singData;
		int score;
		
		int i = 200; //placeholder
		GraphViewData[] data = new GraphViewData[i];
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		singData = extras.getIntegerArrayList("sing");
		melodyData = extras.getIntegerArrayList("melody");
	
	
	
		
		score = 0;
		for (int x = 0 ; x < melodyData.size() && x < singData.size(); x++){
			score = score + Math.abs(melodyData.get(x) - singData.get(x)); //the greater the score, the worse you did, we can then use this somehow
		}
		setContentView(R.layout.activity_grapher);

		
		i = melodyData.size();
		data = 	new GraphViewData[i];
		for (int element = 0;element<melodyData.size(); element++){
			int current = melodyData.get(element);
			data[element] = new GraphViewData(element, current);
					
					
			}
		data[0] = new GraphViewData(0,0);
		
		GraphViewSeriesStyle colour = new GraphViewSeriesStyle();
		colour.setValueDependentColor(new ValueDependentColor(){
			public int get(GraphViewDataInterface data) {
			    // the higher the more red
			    return Color.RED;
			  }
		});
		GraphViewSeriesStyle colour2 = new GraphViewSeriesStyle();
		colour2.setValueDependentColor(new ValueDependentColor(){
			public int get(GraphViewDataInterface data) {
				    // the higher the more red
				return Color.BLUE;
				  }
			
		
		});
		GraphViewSeries melodySeries = new GraphViewSeries("Melody", colour, data);
		
		i = singData.size();
		data = 	new GraphViewData[i];
		for (int element = 0;element<singData.size(); element++){
			int current = melodyData.get(element);
			data[element] = new GraphViewData(element, (current - 100));
					
					
			}
		data[0] = new GraphViewData(0,0);
		
		//im worried that it will create the graphs with a different number of elements, I think we can trunk it to say 200
		//by using a for loop quite easily, need further testing
		
				
		GraphViewSeries singSeries = new GraphViewSeries("Voice", colour2, data);
		
		GraphView graphView = new BarGraphView(this, "Results");		
		graphView.addSeries(singSeries);
		graphView.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView.getGraphViewStyle().setNumHorizontalLabels(0);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		
		
		GraphView graphView2 = new BarGraphView(this, "Results 2");
		graphView2.addSeries(melodySeries);
		graphView2.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView2.getGraphViewStyle().setNumHorizontalLabels(0);
		graphView2.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		
		
		
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.subLayout);
		layout.addView(graphView);
		LinearLayout layout2 = (LinearLayout) findViewById(R.id.subLayout2);
		layout2.addView(graphView2);
		
		
		
	
	}
	
	//public int getScore(){
		//return score;
	//}
	
}

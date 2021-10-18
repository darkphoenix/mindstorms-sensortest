package edu.kit.testgruppe;

import java.util.Arrays;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;

import lejos.hardware.Keys;


/**
 * Example leJOS EV3 Project with an ant build file
 *
 */
public class sensortest {

	public static void main(String[] args) {
		EV3 brick = (EV3) BrickFinder.getLocal();
		TextLCD lcd = brick.getTextLCD();
		Keys keys = ev3.getKeys();
		
	    Port s4 = brick.getPort("S1");
	    EV3ColorSensor sensor = new EV3ColorSensor(s4);
	    
	    SensorMode redMode = sensor.getRedMode();

	    float[] val = new float[redMode.sampleSize()];
	    while(true) {
	    	redMode.fetchSample(val, 0);
	    	lcd.drawString(Arrays.toString(val), 4, 0);
	    	String res;
	    	if(val[0]>0.4)
	    		res = "White";
	    	else
	    		res = "Black";

	    	lcd.drawString(res, 4, 1);

	    	lcd.drawString(""+keys.waitForAnyPress(1), 4, 4);
	    }
	}
	
}

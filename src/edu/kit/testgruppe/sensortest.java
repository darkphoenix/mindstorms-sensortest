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
		Keys keys = brick.getKeys();
		
	    Port s4 = brick.getPort("S1");
	    EV3ColorSensor sensor = new EV3ColorSensor(s4);
	    
	    SensorMode redMode = sensor.getRedMode();

	    float[] val = new float[redMode.sampleSize()];
	    boolean loop = true;
	    while(loop) {
	    	redMode.fetchSample(val, 0);
	    	lcd.drawString(Arrays.toString(val), 4, 0);
	    	String res;
	    	if(val[0]>0.4)
	    		res = "White";
	    	else
	    		res = "Black";

	    	lcd.drawString(res, 4, 1);

	    	if (keys.waitForAnyPress(500) > 0) 
	    		loop = false;
	    }
	    SensorMode rgbMode = sensor.getRGBMode();
	    
	    val = new float[rgbMode.sampleSize()];
	    loop = true;
	    while(loop) {
	    	rgbMode.fetchSample(val, 0);
	    	lcd.drawString(Arrays.toString(val), 0, 0);
	    	String res;
	    	if(val[0]>0.1 && val[1]<0.1 && val[2]<0.1)
	    		res = "Red";
	    	else if(val[0]<0.1 && val[1]>0.08 && val[2]>0.07)
	    		res = "I'm Blue (da be dee da be da)";
	    	else if(val[0]<0.001 && (val[1]<0.001 || val[1]>9) && val[2]<0.001)
	    		res = "AAARGH!";
	    	else
	    		res = "Unknown";

	    	lcd.drawString(res, 0, 1);

	    	if (keys.waitForAnyPress(500) > 0) 
	    		loop = false;
	    }
	}
	
}

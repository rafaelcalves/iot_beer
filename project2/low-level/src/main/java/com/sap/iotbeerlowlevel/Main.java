package com.sap.iotbeerlowlevel;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Logger;

public class Main {
	final static Logger log = Logger.getLogger(Main.class.toString());

	public static void main(String[] args){

		testBlink();
	}

	private static void testBlink(){
		for(int i=0; i<30; i++) {
			toggleLed();
		}
	}

	private static void toggleLed(){

		// create gpio controller
		GpioController gpio = GpioFactory.getInstance();

		// define gpio pin number 1 as an output pin and turn it off
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"MyLED", PinState.LOW);
				
		// use pulse method to set the pin to the HIGH state for
		// an explicit length of time in milliseconds
		myLed.pulse(1000);

		myLed.low(); // OR myLed.setState(PinState.HIGH); OR

		gpio.unprovisionPin(myLed);
	}
}
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

	static GpioController gpio;

	// provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
	static GpioPinDigitalOutput myLed;

	public static void main(String[] args){
		initializePins();

		testBlink();

		// set shutdown state for pin 1 (LED)
		//myLed.setShutdownOptions(true, PinState.LOW);

		/*
		try {
			// toggle pin state for 25 times
			for (int i = 0; i < 25; i++) {
				myLed.toggle();
				log.log(Level.INFO, pin.getState());
				Thread.sleep(2500);
			}
			// done shut down the GPIO controller now
			gpio.shutdown();
		} catch (InterruptedException e) {
			log.log(Level.ERROR, e);
		}
		*/
	}

	private static void initializePins() {
		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// define gpio pin number 1 as an output pin and turn it off
		final GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
																		  "MyLED",           // PIN FRIENDLY NAME (optional)
																		  PinState.LOW);      // PIN STARTUP STATE (optional)
	}

	private static void testBlink(){
		for(int i=0; i<30; i++) {
			toggleLed();
		}
	}

	private static void toggleLed(){
		// use pulse method to set the pin to the HIGH state for
		// an explicit length of time in milliseconds
		myLed.pulse(1000);

		myLed.low(); // OR myLed.setState(PinState.HIGH); OR

		gpio.unprovisionPin(myLed);
	}

	private static void readPinAndWriteOutput(){
		// provision gpio pin #02 as an input pin with its internal pull down resistor enabled
		// (configure pin edge to both rising and falling to get notified for HIGH and LOW state
		// changes)
		GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,             // PIN NUMBER
																	 "MyButton",                   // PIN FRIENDLY NAME (optional)
																	 PinPullResistance.PULL_DOWN); // PIN RESISTANCE (optional)

		// get explicit state enumeration for the GPIO pin associated with the button
		PinState myButtonState = myButton.getState();

		// use convenience wrapper method to interrogate the button state
		boolean buttonPressed = myButton.isHigh();

		gpio.unprovisionPin(myButton);
	}
}
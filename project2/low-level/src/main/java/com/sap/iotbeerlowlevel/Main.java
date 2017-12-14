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

public class Main {

	public static void main(String[] args){

		testBlink();
	}
	private static void testBlink(){
		for(int i=0; i<30; i++) {
			toggleLed();
		}
	}

	private static void toggleLed(){
		// create gpio controller instance
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
																	"My LED",           // PIN FRIENDLY NAME (optional)
																	PinState.LOW);      // PIN STARTUP STATE (optional)

		// explicitly set a state on the pin object
		myLed.setState(PinState.HIGH);

		// use convenience wrapper method to set state on the pin object
		myLed.low();
		myLed.high();

		// use toggle method to apply inverse state on the pin object
		myLed.toggle();

		// use pulse method to set the pin to the HIGH state for
		// an explicit length of time in milliseconds
		myLed.pulse(1000);

		
		gpio.unprovisionPin(myLed);
	}

	private static void readPinAndWriteOutput(){
		final GpioController gpio = GpioFactory.getInstance();

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
	}
}


import time
import RPi.GPIO as GPIO       ## Import GPIO library
import sys

platform1 = 3
platform2 = 5
platform3 = 7
platform4 = 11
platform5 = 13
platform6 = 15
platform7 = 19
platform8 = 21

platforms = [platform1, platform2, platform3, platform4, platform5, platform6, platform7, platform8]

def main():
    GPIO.setmode(GPIO.BOARD)      ## Use board pin numbering
    setupPinsForRead();
    i=0
    while 1:
        print ("----Iteration " + str(i))
        printPinsInput()
        time.sleep (1);
        i = i + 1
        
        
            
def setupPinsForRead():
    for pin in platforms:  
        GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)
        
def printPinsInput():
    print ("--value of the pins: ")
    print ("--")
    for pin in platforms:   
        print (GPIO.input(pin))
        
if __name__ == "__main__":
    main()
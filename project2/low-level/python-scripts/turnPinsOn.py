import time
import RPi.GPIO as GPIO       ## Import GPIO library
import sys

def main():
    GPIO.setmode(GPIO.BOARD)      ## Use board pin numbering
    
    # Iteration over all arguments recevied by the script:
    for eachArg in sys.argv:   
        if eachArg == "turnPinsOn.py":
            print ("--jumping first param")
        else:
            print ("--turning 'on' pin " + eachArg)
            setOutputs(int(eachArg))
            
def setOutputs(pin):
    GPIO.setup(pin, GPIO.OUT) 
    GPIO.output(pin,True)
    
if __name__ == "__main__":
    main()
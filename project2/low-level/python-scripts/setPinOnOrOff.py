import time
import RPi.GPIO as GPIO       ## Import GPIO library
import sys

def main():
    print (" ")
    print (" ")
    GPIO.setmode(GPIO.BOARD)      ## Use board pin numbering
    
    # Iteration over all arguments recevied by the script:
    i=1 #First parameter is always the own file name, so it starts on the second already
    while i < len(sys.argv) - 1:
        currentArg = sys.argv[i]
        nextArg = sys.argv[i+1] 

        if currentArg != "on" and currentArg != "off": #is a pin number
                if nextArg == "on":
                    print ("--turning pin " + currentArg + " to state " + nextArg)
                    setOutputs(int(currentArg), True)
                elif nextArg == "off":
                    print ("--turning pin " + currentArg + " to state " + nextArg)
                    setOutputs(int(currentArg), False)
        i+=1


def setOutputs(pin, state):
    try:
        GPIO.setup(pin, GPIO.OUT) 
        GPIO.output(pin,state)
    except:
        print("ERROR: Wrong parameters. Call the file using the syntax similar to: 'setPinOnOrOff.py 37 off'")
    
if __name__ == "__main__":
    main()

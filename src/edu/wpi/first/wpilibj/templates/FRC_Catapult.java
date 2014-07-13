
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Class representing a pneumatic catapult which is controlled using a single
 * pneumatic piston driven by and a single soleniod. The catapult is retracted 
 * in its default state. When the 'shoot' event is received, the catapult fully 
 * extends and automatically retracts. The catapult can only be shot when it is
 * in the 'retracted' state.
 */
public class FRC_Catapult 
{
    // Catapult states
    protected static final int CATAPULT_RETRACTED  = 0;
    protected static final int CATAPULT_EXTENDING  = 1;
    protected static final int CATAPULT_RETRACTING = 2;
    
    // Catapult state timeout values
    protected static final double CATAPULT_EXTENDING_TIME  = 0.5; // 0.5 seconds
    protected static final double CATAPULT_RETRACTING_TIME = 2.0; // 2.0 seconds
    
    // Catapult state variables
    protected int catapultState;
    protected double enterExtendingStateTime;
    protected double enterRetractingStateTime;

    // Soleniod state
    protected static final boolean SOLENIOD_ON  = true;
    protected static final boolean SOLENIOD_OFF = false;

    // Single soleniod
    protected Solenoid solenoid;
    
    // Controller subsystem
    protected FRC_Controller controller;

    public FRC_Catapult(Solenoid solenoid, FRC_Controller controller) 
    {
        // Default state
        catapultState = CATAPULT_RETRACTED;
        
        this.solenoid = solenoid;
        this.controller = controller;

        enterRetractingStateTime = 0;
        enterExtendingStateTime = 0;
    }
    
    /**
     * Initialize the catapult subsystem
     */
    public void initialise() 
    {
        // Default state of catapult
        catapultState = CATAPULT_RETRACTED;
        
        // Default state of soleniod
        solenoid.set(SOLENIOD_OFF);
        
        enterRetractingStateTime = 0;
        enterExtendingStateTime = 0;
    }
    
    /**
     * Executes the catapult state machine
     */
    public void run() 
    {
        double currentTime = Timer.getFPGATimestamp();
        
        // process 'extending' state
        if (catapultState == CATAPULT_EXTENDING) 
        {
            if (currentTime > enterExtendingStateTime + CATAPULT_EXTENDING_TIME)
            {
                solenoid.set(SOLENIOD_OFF);
                catapultState = CATAPULT_RETRACTING;
                enterRetractingStateTime = Timer.getFPGATimestamp();
            }
        }
        // process 'retracting' state
        else if (catapultState == CATAPULT_RETRACTING) 
        {
            if (currentTime > enterRetractingStateTime + CATAPULT_RETRACTING_TIME)
            {
                catapultState = CATAPULT_RETRACTED;
            }
        }
        // process 'retracted' state
        else 
        {
            if (controller.fireCatapult() == true)
            {
                solenoid.set(SOLENIOD_ON);
                catapultState = CATAPULT_EXTENDING;
                enterExtendingStateTime = Timer.getFPGATimestamp();
            }
        }
    }
}

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

public class FRC_Controller {
    
    protected static final int JOYSTICK_BUTTON_ONE = 1;
    
    protected Joystick joystick;
    
    public FRC_Controller(Joystick joystick)
    {
        this.joystick = joystick;
    }
    
    /**
     * Initialize the controller subsystem
     */
    public void initialise() 
    {
        // Empty
    }
    
    /**
     * Executes the controller state machine
     */
    public void run() 
    {
        // Empty
    }
    
    /**
     * Returns true if the operator would like to fire the catapult
     */
    public boolean fireCatapult()
    {
        return joystick.getRawButton(JOYSTICK_BUTTON_ONE);
    }
}

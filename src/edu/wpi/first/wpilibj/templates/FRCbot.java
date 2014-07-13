/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class FRCbot extends IterativeRobot 
{
    protected static final int SOLENIOD_BREAKOUT_CHANNEL_ONE = 1;
    protected static final int USB_PORT_ONE = 1;
    
    // Declaration of FRCbot subsystems
    protected Joystick joystick;
    protected FRC_Controller controller;
    protected Solenoid catapultSoleniod;
    protected FRC_Catapult catapult;
    protected FRC_Drive drive;
    
    public FRCbot()
    {
        // Call IterativeRobot constructor
        super();
        
        // Construction of the controller subsystem
        joystick = new Joystick(USB_PORT_ONE);
        controller = new FRC_Controller(joystick);  
        
        // Construction of the catapult subsystem
        catapultSoleniod = new Solenoid(SOLENIOD_BREAKOUT_CHANNEL_ONE);
        catapult = new FRC_Catapult(catapultSoleniod, controller);
        
        // Construction of the drive subsystem
        drive = new FRC_Drive(controller);
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        controller.initialise();
        catapult.initialise();
        drive.initialise();
    }

    /**
     * Initialization code for autonomous mode should go here. Users should 
     * override this method for initialization code which will be called each 
     * time the robot enters autonomous mode.
     */
    public void autonomousInit() 
    {

    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        // feed the user watchdog at every period when in autonomous
        Watchdog.getInstance().feed();
    }

    /**
     * Initialization code for teleop mode should go here. Users should override 
     * this method for initialization code which will be called each time the 
     * robot enters teleop mode.
     */
    public void teleopInit() 
    {
        controller.initialise();
        catapult.initialise();
        drive.initialise();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        // feed the user watchdog at every period when in teleop
        Watchdog.getInstance().feed();

        controller.run();
        catapult.run();
        drive.run();
    }
}

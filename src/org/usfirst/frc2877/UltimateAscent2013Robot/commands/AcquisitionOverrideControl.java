/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

/**
 *
 * @author Administrator
 */
public class AcquisitionOverrideControl extends Command {
    
    double posneg = 0;
    double run = Robot.acquisition.ACQUISITIONSPEED;
    boolean overrideUp;
    boolean overrideDown;
    boolean limitSwitchTriggered;
    public AcquisitionOverrideControl() {
        overrideUp = Robot.oi.buttonAcquisitionOverrideUp.get();
        overrideDown = Robot.oi.buttonAcquisitionOverrideDown.get();
        setInterruptible(false);
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.acquisition);
        System.out.println("AcquisitionOverride constructor called");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initialize AcquisitionOverride");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (overrideDown) {
            posneg = -1;
        }
        if (overrideUp) {
            posneg = 1;
        }
        if (!overrideUp ^ !overrideDown) {
            posneg = 0;
        }
        if (overrideUp ^ overrideDown) {
            Robot.acquisition.acquisitionOverride(run, posneg);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       limitSwitchTriggered= Robot.acquisition.rotaryLimitSwitch.get();
       return limitSwitchTriggered;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.acquisition.acquisitionOverride(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

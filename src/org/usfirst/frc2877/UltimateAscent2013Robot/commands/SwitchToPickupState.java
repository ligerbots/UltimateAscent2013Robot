/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.AcquisitionState;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;

/**
 *
 * @author fitzpaj
 */
public class SwitchToPickupState extends Command {
    
    public SwitchToPickupState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // If there are no disks, then we don't need to do anything
        if (Robot.acquisition.numDisks < 1)  {
            end();
        } else {
            // Set acquisition state to PICKUP
            Robot.acquisition.acquisitionState = AcquisitionState.PICKUP;
            // Get lowest disk position and determine how far to lower it
            switch (Robot.acquisition.lowestDisk) {
                case 4: // 
            }
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

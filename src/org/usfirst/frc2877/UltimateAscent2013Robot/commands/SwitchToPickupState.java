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
    
    private AcquisitionScrewControl nextCommand;
    
    public SwitchToPickupState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquisition);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // First, set the new state
        Robot.acquisition.acquisitionState = AcquisitionState.PICKUP;
        // If there are no disks, then we don't need to do anything
        if (Robot.acquisition.numDisks > 0) {
            // Determine how far to move the lowest disk.
            // The lowest disk needs to move to slot 1
            int slotsToMove = 1 - Robot.acquisition.lowestDisk;
            // invoke the AcquisitionScrewControl command to move the screws
            // the required number of turns
            nextCommand = new AcquisitionScrewControl(slotsToMove);
            nextCommand.start();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Nothing to do here since we invoked the next command in initialize()
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // Everything we need is in the initialize() method.
        // We'll just return true.
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

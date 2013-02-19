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
public class SwitchToFeedState extends Command {
    
    private AcquisitionScrewControl nextCommand;
    private ShooterToFeedHeight commandAfterThat;
    private int slotsToMove = 0;
    
    public SwitchToFeedState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquisition);
    } 

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.acquisition.acquisitionState = AcquisitionState.FEED;
        // If there are no disks, then we don't need to do anything
        if (Robot.acquisition.numDisks > 0) {
            // Determine how far to move the lowest disk.
            // The highest disk needs to move to slot 4
            slotsToMove = 2 - Robot.acquisition.highestDisk;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // This command only goes through once so this should always rturn true.
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        // invoke the AcquisitionScrewControl command to move the screws
        // the required number of turns
        if (slotsToMove != 0) {
            nextCommand = new AcquisitionScrewControl(slotsToMove);
            nextCommand.start();
        }
        // Since AcquisitionScrewControl requires the Acquisition subsystem,
        // we can also invoke the ShooterToFeedHeight command which requires
        // the Shooter subsystem.  This should be invoked unconditionally.
        commandAfterThat = new ShooterToFeedHeight();
        commandAfterThat.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

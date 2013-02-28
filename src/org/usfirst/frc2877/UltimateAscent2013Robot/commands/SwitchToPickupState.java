/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.AcquisitionState;

/**
 *
 * @author fitzpaj
 */
public class SwitchToPickupState extends CommandGroup {
    
    private int m_slotsToMove = 0;
    
    public SwitchToPickupState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // requires(Robot.acquisition);
        // I commented out the above call to requires() when I changed this
        // to a CommandGroup.  Each individual command will have its own
        // call to requires()
        // The examples show the addSequential() and addParallel() calls
        // done in the constructor so we'll do it that way too.
        
        // First we should set the acquisitionState
        Robot.acquisition.acquisitionState = AcquisitionState.FEED;

        // Next we have to compute how many m_slotsToMove to pass in to
        // the AcquisitionScrewControl command
        // If there are no disks, then we don't need to do anything
        if (Robot.acquisition.numDisks > 0) {
            // Determine how far to move the lowest disk.
            // The highest disk needs to move to slot 4
            m_slotsToMove = 1 - Robot.acquisition.highestDisk;
        }
        
        // Now add the commands
        addSequential(new AcquisitionScrewControl(m_slotsToMove));
        // To save time, raise the shooter to the feeder height in parallel
        addParallel(new ShooterToFeedHeight());
    }
}

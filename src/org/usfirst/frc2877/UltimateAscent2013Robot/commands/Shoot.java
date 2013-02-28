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
public class Shoot extends CommandGroup {
    
    private int m_slotsToMove = 0;
       
    public Shoot() {

        Robot.acquisition.acquisitionState = AcquisitionState.SHOOT;
        // If there are no disks, then we don't need to do anything
        if (Robot.acquisition.numDisks > 0) {
            // Determine how far to move the lowest disk.
            // The highest disk needs to move to slot 4
            m_slotsToMove = 4 - Robot.acquisition.highestDisk;
            // Since no disk can live in slot4, no need to check if 
            // slotsToMove != 0
        }
        // Now add the commands
        addSequential(new AcquisitionScrewControl(m_slotsToMove));
    }
}

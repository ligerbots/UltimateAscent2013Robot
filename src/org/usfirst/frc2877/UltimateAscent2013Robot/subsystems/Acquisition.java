// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;


/**
 *
 */
public class Acquisition extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Relay acquisitionRoller = RobotMap.acquisitionRoller;
    CANJaguar acquisitionScrewlift = RobotMap.acquisitionScrewlift;
//    Encoder acquisitionScrewEncoder = RobotMap.acquisitionAcquisitionScrewEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public DigitalInput rotaryLimitSwitch = RobotMap.acquisitionRotaryLimitSwitch;
    // Define how many slots that can hold disks
    public static final int NUMPOSITIONS = 4;
    // Initialize the local variables
    public static int m_lowestDisk = 4;
    public static int m_highestDisk = -1;
    public static int m_numDisks = 0;
    // TODO: set this threshold once we can measure it
    public static double threshold = 1.5;
    public static final double ACQUISITIONSPEED = .5;
    public static final double SHOOTLOADSPEED = .5;
    
    // diskPositions is what we think the current disk positions are
    public static boolean[] diskPositions = new boolean[NUMPOSITIONS];
    
    public AcquisitionState acquisitionState = AcquisitionState.SHOOT;
    public int m_direction = 0;
    public int m_last_direction = 0;
    public boolean bottomSwitch = false;
    public boolean topSwitch = false;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void Acquisition()
    {
        // not clear we'll ever get these on the robot
        for (int i=0; i!=NUMPOSITIONS; i++) {
            diskPositions[i] = false;
        }
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        System.out.println("Disk Acquisition initDefaultCommand called.");
    }
    
    // This is emergency disk clear for when our lift messes up and we clear
    // on timeout
    public void clearDisks() {
        for (int i=0; i!=NUMPOSITIONS; i++) {
             diskPositions[i] = false;
        }
    }
    
    
    // return true if there was a change in values that requires action
    public int refreshValues()
    {
        // The bottom and top switches are wired to be normally open
        // Therefore, when the switch is tripped it reads FALSE
        // We invert the sense so bottomSwitch = true or topSwitch=true
        // means a disk is present
        bottomSwitch = !RobotMap.bottomAcquisitionSwitch.get();
        topSwitch =  !RobotMap.topAcquisitionSwitch.get();
        // We only act on a transition of the acquisition sensors if we're not 
        // in the process of raising or lower a disk
        if (m_direction == 0) {
            // The top switch only registers if we weren't just lifting
            if (false && topSwitch && m_last_direction != 1) {
                // Is there a disk at the bottom position?
                System.out.println("======= Top switch hit =======");
                if (!diskPositions[0]) {
                    System.out.println("======= LOWERING =======");
                    m_direction = -1;
                }
                // note that a disk just loaded
                diskPositions[NUMPOSITIONS - 1] = true;
            }
            // Do we have a new disk?
            if (bottomSwitch) {
                // Is there a disk in the top position?
                System.out.println("======= Bottom switch hit =======");
                if (!diskPositions[NUMPOSITIONS - 1]) {
                    System.out.println("======= LIFTING =======");
                    m_direction = 1;
                }
                // note that a disk just loaded
                diskPositions[0] = true;
            }
        }
        return m_direction;
    }
    // we update the disk positions -- one at a time, up or down
    public int updateDiskPositions(int direction)
    {
        System.out.println("updateDiskPositions " + direction);
        int count = 0, highestdisk = 0, lowestdisk = 3;
        if (direction > 0) {
            // we move the disks up from bottom to top
            for (int i=NUMPOSITIONS-1; i>0; i--) {
                System.out.println("Moving " + (i-1) +  " to " + i + 
                        (diskPositions[i-1] ? " with disk." : " without disk."));
                diskPositions[i] = diskPositions[i-1];
                if (diskPositions[i]) { 
                    count++;
                    if (i>highestdisk) { highestdisk = i; }
                    if (i<lowestdisk) {lowestdisk = i; }
                }
             }
            diskPositions[0] = false;
        }
        else {
            // we move the disks down from top to bottom
            for (int i=0; i<NUMPOSITIONS-1; i++) {
                System.out.println("Moving " + (i+1) +  " to " + i + 
                        (diskPositions[i+1] ? " with disk." : " without disk."));
                diskPositions[i] = diskPositions[i+1];
                if (diskPositions[i]) {
                    count++;
                    if (i>highestdisk) { highestdisk = i; }
                    if (i<lowestdisk) { lowestdisk = i; }
                }
            }
            diskPositions[NUMPOSITIONS-1] = false;
        }
        m_lowestDisk = lowestdisk;
        m_highestDisk = highestdisk;
        m_numDisks = count;
        return count;
   }
        
    public void acquisitionTurnScrews(double run)
    {
            try {
                acquisitionScrewlift.setX(run);
            } catch (CANTimeoutException ex) {
                System.out.println("Timeout Exception on acquisitionScrewlift.setX in acquisitionOverride");
            }
    }
}
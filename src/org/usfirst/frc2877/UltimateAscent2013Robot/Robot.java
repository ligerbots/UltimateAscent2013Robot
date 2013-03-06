// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Acquisition acquisition;
    public static Climb climb;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Drive drive;
    public ShooterAngleControl shooterElevationControl;
    public static int m_total_ticks = 0;
    public static boolean m_shooter_enable = false;
    // This constant is used to define how many cycles we need to go to make
    // sure that the cam has cleared the limit switch
    public static int OVERSHOOT_AMOUNT_UP = 8;
    public static int OVERSHOOT_AMOUNT_DOWN = 11;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        acquisition = new Acquisition();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drive = new Drive();

        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // We need to initialize the disk positions
        // This is our presumed automous setup
        Acquisition.diskPositions[3] = true;
        Acquisition.diskPositions[2] = true;
        Acquisition.diskPositions[1] = true;
        Acquisition.diskPositions[0] = false;
        Acquisition.m_highestDisk = 3;
        Acquisition.m_lowestDisk = 1;
        Acquisition.m_numDisks = 3;
        updateDashboard();
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        commonPeriodic();
        Scheduler.getInstance().run();
     }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        System.out.println("teleopInit");
        if (autonomousCommand != null) autonomousCommand.cancel();
        // start the drive command.  This will remain active during teleop
        drive.start();
        // create and start the shooterElevationControl command 
       
        shooterElevationControl = new ShooterAngleControl();
        shooterElevationControl.start();

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        commonPeriodic();
        Scheduler.getInstance().run();
    }
    
    private void commonPeriodic() {
        m_total_ticks++;
        // check the shooter elevation angle
        shooter.shooterElevationAngle();
        
        // Check our acquisition sensors.
        // returns 1 if we need to lift a disk, -1 if we need to lower
        // and 0 if there's nothing to do
        switch (acquisition.refreshValues())
        {
            case 1:
                oi.screwupOne.start();
                break;
            case -1:
                oi.screwDownOne.start();
                break;
        }

        updateDashboard();
    }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    // update frequency in times per second
    public static final int FREQUENCY = 10;
    public static final int TICKS_PER_SECOND = 50;
    public static int m_count = TICKS_PER_SECOND/FREQUENCY;
    public static void updateDashboard()
    {
        if (--m_count==0)
        {
            m_count = TICKS_PER_SECOND/FREQUENCY;
            SmartDashboard.putNumber("Ticks", m_total_ticks);

            RobotMap.jags.UpdateDashboard();
              
            // presume a compass range of 0 to 125, zero is at about 105
            double compassAngle = 105-(Robot.shooter.currentShooterAngle/2);
            SmartDashboard.putNumber("Shooter compass", compassAngle);
            SmartDashboard.putNumber("Shooter angle", Robot.shooter.currentShooterAngle);
            SmartDashboard.putNumber("Shooter angle volts", Robot.shooter.shooterElevationVoltage);

            SmartDashboard.putBoolean("Disk 0", Acquisition.diskPositions[0]);
            SmartDashboard.putBoolean("Disk 1", Acquisition.diskPositions[1]);
            SmartDashboard.putBoolean("Disk 2", Acquisition.diskPositions[2]);
            SmartDashboard.putBoolean("Disk 3", Acquisition.diskPositions[3]);

            SmartDashboard.putNumber("Lowest disk", Acquisition.m_lowestDisk);
            SmartDashboard.putNumber("Highest disk", Acquisition.m_highestDisk);

            SmartDashboard.putNumber("OVERSHOOT_AMOUNT_UP", OVERSHOOT_AMOUNT_UP);
            SmartDashboard.putNumber("OVERSHOOT_AMOUNT_DOWN", OVERSHOOT_AMOUNT_DOWN);
            
            SmartDashboard.putBoolean("Acquisition Roller", 
                    RobotMap.acquisitionRoller.get()==Relay.Value.kOn ? true : false);
            
            SmartDashboard.putBoolean("Shooter enable", m_shooter_enable);

         }
        
    }
    
    public static void debugOut(String label, String value)
    {
        System.out.println(label + " " + value);
        SmartDashboard.putString(label, value);
    }
    
    public static void debugOutBoolean(String label, boolean value)
    {
        System.out.println(label + " " + (value ? "true" : "false"));
        SmartDashboard.putBoolean(label, value);
    }

    public static void debugOutNumber(String label, double number)
    {
        System.out.println(label + " " + number);
        SmartDashboard.putNumber(label, number);
    }
}

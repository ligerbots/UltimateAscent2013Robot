// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class  AutonomousCommand extends CommandGroup {
    private final static double DELAY_SECONDS = 5.5;
    private final static int MOVETOANGLE = 30;
    int m_count;

    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES;
        addParallel(new ShooterEnable(true));
       // addParallel(new AutonomousGoToAngle(MOVETOANGLE));
        addSequential(new Delay(DELAY_SECONDS));
        addSequential(new AcquisitionScrewControl(1, false));
        addSequential(new Delay(0.4));
        addSequential(new AcquisitionScrewControl(1, false));
        addSequential(new Delay(0.5));
        addSequential(new AcquisitionScrewControl(1, false));
        addSequential(new Delay(1.0));
        addSequential(new AcquisitionScrewControl(1, false));
        addSequential(new Delay(1.0));
        addSequential(new AcquisitionScrewControl(1, false));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;

import java.lang.Math;

/**
 *
 * @author fitzpaj
 */
public class ShooterToFeedHeight extends Command {
    
    // TODO: Set this to the right value after the robot is built
    private final double ANGLE_FOR_FEEDER = 45;
    // Used to control the direction of the motor
    private int direction = 1;
    private final double FEED_ANGLE_THRESHOLD = 1;
    
    public ShooterToFeedHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if ( Robot.shooter.currentShooterAngle > ANGLE_FOR_FEEDER ) {
            direction = -1;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.runShooterAngle(direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // Check to see if we're close enough.
        return (direction * (ANGLE_FOR_FEEDER - Robot.shooter.currentShooterAngle)) < FEED_ANGLE_THRESHOLD;
    }

    // Called once after isFinished returns true
    protected void end() {
        // stop the shooter angle motor
        Robot.shooter.runShooterAngle(0);
        // re-enable the ShooterAngleControl command
//        StartCommand(shooterElevationControl());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

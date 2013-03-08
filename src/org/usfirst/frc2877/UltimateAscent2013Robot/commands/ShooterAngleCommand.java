/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

/**
 *
 * @author fitzpaj
 */
public class ShooterAngleCommand extends Command {
    
    public ShooterAngleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);       
        requires(Robot.shooterAngleControl);
        System.out.println("ShooterAzimuth constructor called");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initialize ShooterAzimuth");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // negative here because we were raising backwards
        double y = -Robot.oi.joystick2.getY();
        Robot.shooterAngleControl.runShooterAngle(y);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooterAngleControl.runShooterAngle(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;

/**
 *
 * @author fitzpaj
 */
public class ShooterEnable extends Command {

    int m_count = 10;

    public ShooterEnable() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);

    }

    // Called just before this Command runs the first time
    public void initialize() {
        double frontWheel = 0.0, backWheel = 0.0;
        Robot.m_shooter_enable = !Robot.m_shooter_enable;
        Robot.debugOutBoolean("Shooter enable", Robot.m_shooter_enable);
        
        if (Robot.m_shooter_enable) {
            frontWheel = -1.0;
            backWheel = -0.5;
        }
        try {
            RobotMap.shooterFrontWheel.setX(frontWheel);
            RobotMap.shooterBackWheel.setX(backWheel);
        } catch (Exception ex) {
            System.out.println("Shooter motors speed set failed");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // finishes immediately
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

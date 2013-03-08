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
    boolean m_initial_enable;
    int m_count = 10;

    public ShooterEnable(boolean enable) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_initial_enable = enable;
        
        requires(Robot.shooter);

    }

    // Called just before this Command runs the first time
    public void initialize() {
        if (m_initial_enable) {
            Robot.m_shooter_enable = true;
            m_initial_enable = false;
        }
        else {
                Robot.m_shooter_enable = !Robot.m_shooter_enable;
        }
        Robot.debugOutBoolean("Shooter enable", Robot.m_shooter_enable);
        Robot.shooter.runShooter(Robot.m_shooter_enable);
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

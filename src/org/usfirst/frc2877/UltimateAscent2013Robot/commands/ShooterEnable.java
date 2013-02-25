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
public class ShooterEnable extends Command {
    int m_last_tick;
    public ShooterEnable() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
       m_last_tick = Robot.m_total_ticks;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
         
         Robot.m_shooter_enable = false;
    
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

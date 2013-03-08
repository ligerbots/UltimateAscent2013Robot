/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Administrator
 */
public class AcquisitionRoller extends Command {
    
    public static boolean m_enable = false;
    public static int m_tristate = 0;
    
    public AcquisitionRoller() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        System.out.println("Drive constructor called");

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_enable = !m_enable;
        Relay.Value m_direc = m_enable ? Relay.Value.kForward : Relay.Value.kOff;
        RobotMap.acquisitionRoller.set(m_direc);
        Robot.debugOutBoolean("Acquisition roller", m_enable);
     }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
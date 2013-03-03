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
 * @author Administrator
 */
public class OvershootChange extends Command {
        private int m_direction;
        private int m_value;

        public OvershootChange(int direction, int value) 
        {
            m_direction = direction;
            m_value = value;
         }
        
        protected void initialize() {
            if (m_direction>0) {
               Robot.OVERSHOOT_AMOUNT_UP += m_value; 
            }
            else {
               Robot.OVERSHOOT_AMOUNT_DOWN += m_value;  
            }
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


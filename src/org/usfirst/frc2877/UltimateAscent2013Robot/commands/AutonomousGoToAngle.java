/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Administrator
 */
public class AutonomousGoToAngle extends Command {
        public int m_movetoangle;
        int m_count = 10;
        boolean m_finished;

        public AutonomousGoToAngle(int movetoangle) 
        {        
            // not the right place to put this, but..
            Robot.shooter.runShooter(true);
            m_movetoangle = movetoangle;
         }
        
        protected void initialize() {
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {
           m_finished = Robot.shooter.moveToAngle(m_movetoangle);
           if (--m_count==0) {
               m_count = 10;
               System.out.println("Autonomous MoveToAngle: " + Robot.m_total_ticks);
           }
        }

        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {
            return m_finished;
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

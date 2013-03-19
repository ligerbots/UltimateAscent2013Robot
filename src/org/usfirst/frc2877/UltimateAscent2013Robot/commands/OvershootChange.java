/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import java.io.DataOutputStream;
import com.sun.squawk.microedition.io.FileConnection;
import java.io.DataInputStream;
import javax.microedition.io.Connector;

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
            try {
                // Persist our overshoot numbers to a file
                DataOutputStream file;
                FileConnection fc;
                fc = (FileConnection)Connector.open(Robot.OVERSHOOT_FILE, Connector.WRITE);
                System.out.println("Saving " + Robot.OVERSHOOT_FILE);
                fc.create();
                file = fc.openDataOutputStream();
                file.writeInt(Robot.OVERSHOOT_AMOUNT_UP);
                System.out.println(Robot.OVERSHOOT_AMOUNT_UP);
                file.writeInt(Robot.OVERSHOOT_AMOUNT_DOWN);
                System.out.println(Robot.OVERSHOOT_AMOUNT_DOWN);
                file.flush();
                file.close();
                fc.close();
            }
            catch (Exception ex) {
                System.out.println("File output error: " + ex.getMessage());
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


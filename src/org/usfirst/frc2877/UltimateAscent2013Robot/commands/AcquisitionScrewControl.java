/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
/**
 *
 * @author Administrator
 */
public class AcquisitionScrewControl extends CommandGroup {
    
    // save the requested number of turns
    private int m_requestedTurns = 0;
    // keep track of how many turns
    private int m_turns;
    // keep track of how many iterations we've done
    private int m_numCycles = 0;

    // In case the switch can be set for multiple iterations
    // It's initialized to false because we won't check it until after we have
    // run MIN_CYCLLES_TO_CLEAR iterations.
    // m_active start out false if we're already in contact with the switch
    // it goes true, once we're not in contact with the switch
    private static boolean m_armed = false;
    private static int overshootCycles = 0;
    public int m_direction;
    // The default speed for the screws
    boolean limitSwitchTriggered;
    int m_count = 10;
    public AcquisitionScrewControl(int turns) {
        setInterruptible(false);
        m_requestedTurns = turns;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.acquisition);
        System.out.println("AcquisitionOverride constructor called with turns=" + turns);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // If we start out in contact with the switch, then we're "inactive"
        // (for switch sensing), until we get out of contact
        m_armed = !RobotMap.acquisitionRotaryLimitSwitch.get();
        overshootCycles = 0;
        // if M-requested turns is less than zero, then we have to move the
        // screws down, so make the speed negative.
        m_direction = 1;
        if (m_requestedTurns < 0) {
            if (Robot.acquisition.m_lowestDisk + m_requestedTurns >= 0 &&
                    !RobotMap.bottonAcquisitionSwitch.get()) {
                m_direction = -1;
            }
            else {
                // If we've got a disk at the bottom, we don't go down!
                m_direction = 0;
            }
        }

        // Don't depend on these being zero from the declaration init
        // It's not clear if we get a new copy of this class each time
        m_numCycles = 0;
        m_turns = 0;
        System.out.println("AcquisitionOverride requested Turns: " + m_requestedTurns);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean limit = RobotMap.acquisitionRotaryLimitSwitch.get();

        if (--m_count == 0) {
            m_count = 10;
            System.out.println("m_numCycles = " + m_numCycles + " m_turns = " + m_turns
                    + " Direction = " + m_direction);
        }
        
        if (m_direction != 0) {
            System.out.println("Limit: " + (limit?"true":"false") + " | Active: " + 
                                (m_armed?"true":"false") + " | m_numCycles: " +
                                m_numCycles + " | overshootCycles: " + overshootCycles);
            if (--overshootCycles < 0) {
                // we stay inactive as long as the limit switch continues to be true
                // this lets us clear if we started out in contact
                if (!m_armed && !limit) {
                    m_armed = true;
                }
                if (m_armed && limit) {
                    System.out.println("**** Active limit switch contacted. ****");
                    m_turns += m_direction;
                    // if we have completed the requested number of turns,
                    // we can go straight to end.
                    overshootCycles = m_direction>0 ?  
                                    Robot.OVERSHOOT_AMOUNT_UP: Robot.OVERSHOOT_AMOUNT_DOWN ;
                    System.out.println("Turn " + m_turns + " of " + m_requestedTurns);
                }
            }
            else {
                if (overshootCycles == 0) {
                    // once we've completed our overshoot amount, we're done with a turn
                    Robot.acquisition.updateDiskPositions(m_direction);
                    if (m_turns++ == m_requestedTurns) {
                        // Once we've
                        Robot.acquisition.acquisitionTurnScrews(0);
                        Robot.acquisition.m_direction = m_direction = 0;
                        System.out.println("************** Finished a full turn ****************");
                        return;
                    }
                }
            } 
        }

        // Since the switch is not contacted, we have not completed
        // the current turn, so keep going.
        Robot.acquisition.acquisitionTurnScrews(m_direction * Robot.acquisition.ACQUISITIONSPEED);
        // increment the number of cycles
        m_numCycles++;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // It is finished it we have completed the right number of turns.
        // So the switch must be contacted and m_turns must be equal to the
        // requested number. I made it >= just in case.
        return (m_direction==0 || (m_turns == m_requestedTurns && overshootCycles <= 0));
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

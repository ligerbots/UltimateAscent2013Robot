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
    private static int m_overshootCycles = 0;
    // The default speed for the screws
    public int m_direction;
    boolean limitSwitchTriggered;
    int m_count = 10;
    boolean m_outputted;
    boolean m_calledBySwitch;
    
    public AcquisitionScrewControl(int turns, boolean calledBySwitch) {
        setInterruptible(false);
        m_calledBySwitch = calledBySwitch;
        m_requestedTurns = turns;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.acquisition);
        System.out.println("AcquisitionOverride constructor called with turns=" + turns);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
         // If we start out in contact with the switch, then we're not "armed"
        // (for switch sensing), until we get out of contact
        m_armed = !RobotMap.acquisitionRotaryLimitSwitch.get();
        m_overshootCycles = 0;
        // if M-requested turns is less than zero, then we have to move the
        // screws down, so make the speed negative.
        m_direction = 1;
        if (m_requestedTurns < 0) {
            // Safety -- don't go down if a disk it at the bottom
            if (Robot.acquisition.m_lowestDisk + m_requestedTurns >= 0 &&
                    // remember bottomAcquisitionSwitch.get() is inverted
                    // true means a disk is not present
                    RobotMap.bottomAcquisitionSwitch.get()) {
                m_direction = -1;
            }
            else {
                // If we've got a disk at the bottom, we don't go down!
                    m_direction = 0;
            }
        }

        m_numCycles = 0;
        m_turns = 0;
        System.out.println("AcquisitionOverride requested Turns: " + m_requestedTurns);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean limit = RobotMap.acquisitionRotaryLimitSwitch.get();
        m_outputted = false;
        if (m_direction != 0) {
            if (--m_overshootCycles < 0) {
                // we stay inactive as long as the limit switch continues to be true
                // this lets us clear if we started out in contact
                if (!m_armed && !limit) {
                    m_armed = true;
                }
                if (m_armed && limit) {
                    System.out.println("**** Rotary limit switch contacted. ****");
                    // if we have completed the requested number of turns,
                    // we can go straight to end.
                    m_overshootCycles = m_direction > 0
                            ? Robot.OVERSHOOT_AMOUNT_UP : Robot.OVERSHOOT_AMOUNT_DOWN;
                    outputTurnInfo(limit);
                }
            } else {
                if (m_overshootCycles == 0) {
                    outputTurnInfo(limit);
                    // once we've completed our overshoot amount, we're done with a turn
                    Robot.acquisition.updateDiskPositions(m_direction);
                    if ((m_turns += m_direction) == m_requestedTurns) {
                        // Once we've
                        Robot.acquisition.acquisitionTurnScrews(0);
                        Robot.acquisition.m_last_direction = m_direction;
                        m_direction = 0;
                        Robot.acquisition.m_direction = m_direction;
                        m_outputted = false;
                        outputTurnInfo(limit);
                        System.out.println("************** Finished a full turn ****************");
                        return;
                    }
                }
            }
            // safety code -- we never run more than a full cycle (presumed to be 48
            // turns), past requested turns, even 
            if (m_numCycles++ == (24 + 24 * Math.abs(m_requestedTurns))) {
                outputTurnInfo(limit);
                Robot.acquisition.acquisitionTurnScrews(0);
                Robot.acquisition.m_last_direction = m_direction;
                Robot.acquisition.updateDiskPositions(m_direction * 2);
                Robot.acquisition.m_direction = m_direction = 0;
                m_turns = m_requestedTurns;     // pretend we're done
                System.out.println("************** !!SAFETY STOPPED COILS at " + m_numCycles
                        + " CYCLES !! ****************");
                return;
            }
            // Since the switch is not contacted, we have not completed
            // the current turn, so keep going.
            Robot.acquisition.acquisitionTurnScrews(m_direction *Robot.acquisition.ACQUISITIONSPEED);
            // increment the number of cycles

            if (--m_count == 0) {
                m_count = 5;
                outputTurnInfo(limit);      // if we haven't yet done so
            }
        }
    }
    
    // Call this to output info whenever something interesting changes,
    // or every N cycles (right now N = 5)
    private void outputTurnInfo(boolean limit) {
        if (!m_outputted) {
            System.out.println("Limit: " + (limit?"true":"false") + " | Armed: " + 
                (m_armed?"true":"false") + " | m_numCycles: " +
                m_numCycles + " | overshootCycles: " + m_overshootCycles +
                    " | direc: " + m_direction + 
                    " | m_turns: " + m_turns);
            m_outputted = true;
        }
    }
            

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean finished=false;
        if(m_calledBySwitch == false) {
            finished = m_direction==0 || (m_turns == m_requestedTurns && m_overshootCycles <= 0);
        }
        else if(m_calledBySwitch == true) {
            finished = Robot.acquisition.m_direction==0 ||(m_turns == m_requestedTurns && m_overshootCycles <= 0);
        }
        // It is finished it we have completed the right number of turns.
        // So the switch must be contacted and m_turns must be equal to the
        // requested number. I made it >= just in case.
        if (finished) {
            m_outputted = false;
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@ FINISHED @@@@@@@@@@@@@@@@@@@@@@");
            outputTurnInfo(RobotMap.acquisitionRotaryLimitSwitch.get()); 
        }
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.acquisition.acquisitionTurnScrews(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

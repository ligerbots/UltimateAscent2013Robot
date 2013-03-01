/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;
import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
import org.usfirst.frc2877.UltimateAscent2013Robot.subsystems.ScrewState;
/**
 *
 * @author Administrator
 */
public class AcquisitionScrewControl extends Command {
    
    // save the requested number of turns
    private int m_requestedTurns = 0;
    // keep track of how many turns
    private int m_turns;
    // keep track of how many iterations we've done
    private int m_numCycles = 0;
    // This constant is used to define how many cycles we need to go to make
    // sure that the cam has cleared the limit switch
    private final int MIN_CYCLES_TO_CLEAR = 30;
    // In case the switch can be set for multiple iterations
    // It's initialized to false because we won't check it until after we have
    // run MIN_CYCLLES_TO_CLEAR iterations.
    private boolean m_switchContacted = false;
    // The default speed for the screws
    double run = Robot.acquisition.ACQUISITIONSPEED;
    boolean limitSwitchTriggered;
    int m_count = 10;
    public AcquisitionScrewControl(int turns) {
        setInterruptible(false);
        m_requestedTurns = turns;
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.acquisition);
        System.out.println("AcquisitionOverride constructor called");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        // if M-requested turns is less than zero, then we have to move the
        // screws down, so make the speed negative.
        Robot.acquisition.screwState = ScrewState.LIFTING;
        if (m_requestedTurns < 0) {
            Robot.acquisition.screwState = ScrewState.LOWERING;
            run = -1 * run;
        }
        // Don't depend on these being zero from the declaration init
        // It's not clear if we get a new copy of this class each time
        m_numCycles = 0;
        m_turns = 0;
        System.out.println("Initialize AcquisitionOverride " + m_requestedTurns);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean limit = RobotMap.acquisitionRotaryLimitSwitch.get();
       
        if (--m_count==0)
        {
            m_count = 10;
            System.out.println("m_numCycles = " + m_numCycles + " m_turns = " + m_turns);
        }

        // If the switch state has changed, then if it is now true that means
        // we just finished a rotation.
        if (limit != m_switchContacted)
        {
            System.out.println("**** Limit switch contacted. ****");
            // We need to run a few cycles before we check for the switch or else
            // it might stop immediately.
            if (m_numCycles > MIN_CYCLES_TO_CLEAR && m_switchContacted)
            {
                Robot.acquisition.updateDiskPositions(run > 0 ? 1 : -1);

                System.out.println("Cleared " + MIN_CYCLES_TO_CLEAR + " cycles");
                m_turns++;
                
                // if we have completed the requested number of turns,
                // we can go straight to end.
                if (m_turns == m_requestedTurns)
                {
                    System.out.println("End screw turns");
                    end();
                }
            System.out.println("Turn " + m_turns + " of " + m_requestedTurns);
            }
        }
        // Since the switch is not contacted, we have not completed
        // the current turn, so keep going.
        Robot.acquisition.acquisitionTurnScrews(run);

        // Save the current state of the switch to check against next iteration
        m_switchContacted = limit;
        // increment the number of cycles
        m_numCycles++;
    }
  

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // It si finished it we have completed the right number of turns.
        // So the switch must be contacted and m_turns must be equal to the
        // requested number. I made it >= just in case.
        Robot.acquisition.screwState = ScrewState.NEUTRAL;
        return (m_turns >= m_requestedTurns);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.acquisition.acquisitionTurnScrews(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

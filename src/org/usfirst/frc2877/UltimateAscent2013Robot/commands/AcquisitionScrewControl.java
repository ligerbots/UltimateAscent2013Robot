/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

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
    private int numCycles = 0;
    // This constant is used to define how many cycles we need to go to make
    // sure that the cam has cleared the limit switch
    private final int MIN_CYCLES_TO_CLEAR = 20;
    // In case the switch can be set for multiple iterations
    // It's initialized to false because we won't check it until after we have
    // run MIN_CYCLLES_TO_CLEAR iterations.
    private boolean switchContacted = false;
    // The default speed for the screws
    double run = Robot.acquisition.ACQUISITIONSPEED;
    boolean limitSwitchTriggered;
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
        System.out.println("Initialize AcquisitionOverride");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // We need to run a few cycles before we check for the switch or else
        // it migh stop immediately
        if (numCycles > MIN_CYCLES_TO_CLEAR) {
            // The first time we get here, the limit switch will be false
            // If the switch is true, that mean we've made one revolution.
            if (Robot.acquisition.rotaryLimitSwitch.get() != switchContacted) {
                switchContacted = Robot.acquisition.rotaryLimitSwitch.get();
                // if the switch is true, that means we just finished a turn
                if (switchContacted) {
                    m_turns++;
                }
            } else {
                Robot.acquisition.acquisitionTurnScrews(run);
            }


            numCycles++;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       limitSwitchTriggered= Robot.acquisition.rotaryLimitSwitch.get();
       return limitSwitchTriggered;
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

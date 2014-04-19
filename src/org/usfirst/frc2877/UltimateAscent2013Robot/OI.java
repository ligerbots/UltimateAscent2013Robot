// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot;

import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick joystick1;
    public Joystick joystick2;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton buttonMecanum;
    public JoystickButton buttonShooterEnable;
    public JoystickButton buttonAcquisitionRoller;
    public JoystickButton buttonShooterShoot;
    public JoystickButton buttonAcquisitionOverrideDown;
    public JoystickButton buttonAcquisitionOverrideUp;
    public AcquisitionScrewControl screwDownOne = new AcquisitionScrewControl(-1, false);
    public AcquisitionScrewControl screwUpOne = new AcquisitionScrewControl(1, false);
    public AcquisitionRoller roller = new AcquisitionRoller();
    public AutonomousDrive autoDrive = new AutonomousDrive(30);
    public JoystickButton autoDriveButton;
    
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        joystick1 = new Joystick(1);
        joystick2 = new Joystick(2);        
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        //SmartDashboard.putData("DriveArcade", new DriveArcade());

        //SmartDashboard.putData("DriveMecanum", new DriveMecanum());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        

        // Button9 enables mecanum drive
        buttonMecanum = new JoystickButton(joystick1,9);
        JoystickButton overShootUpPlus = new JoystickButton(joystick2, 11);
        JoystickButton overShootUpMinus = new JoystickButton(joystick2, 10);
        JoystickButton overShootDownMinus= new JoystickButton(joystick2, 7);
        JoystickButton overShootDownPlus = new JoystickButton(joystick2, 6);
        
        overShootUpPlus.whenPressed(new OvershootChange(1,1));
        overShootUpMinus.whenPressed(new OvershootChange(1,-1));
        overShootDownPlus.whenPressed(new OvershootChange(-1,1));
        overShootDownMinus.whenPressed(new OvershootChange(-1,-1));
        
        buttonShooterEnable = new JoystickButton(joystick1, 6);
        buttonShooterEnable.whenPressed(new ShootToggle());
        
        buttonAcquisitionRoller = new JoystickButton(joystick1, 5);
        buttonAcquisitionRoller.whenPressed(roller);
        
        autoDriveButton = new JoystickButton(joystick1, 1);
        autoDriveButton.whenPressed(autoDrive);
        
        // joystick2 is the shooter's joystick.  This is an arcade joystick
        // button1 is the shoot button
        buttonShooterShoot = new JoystickButton(joystick2, 1);
        // button2 moves the acquisition screw down one turn
        buttonAcquisitionOverrideDown = new JoystickButton(joystick2, 2);
        buttonAcquisitionOverrideDown.whenPressed(screwDownOne);
        // button3 moves the acquisition screw up one turn
        buttonAcquisitionOverrideUp = new JoystickButton(joystick2, 3);
        buttonAcquisitionOverrideUp.whenPressed(screwUpOne);
        // button6 moves the shooter angle to preset height for feeder station

    }
    
    
    public Joystick getJoystick1() {
        return joystick1;
    }

    public Joystick getJoystick2() {
        return joystick2;
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}


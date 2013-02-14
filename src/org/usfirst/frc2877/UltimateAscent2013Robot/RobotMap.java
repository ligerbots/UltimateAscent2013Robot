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
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.Encoder.PIDSourceParameter;
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANJaguar driveTrainJaguarLeftFront;
    public static CANJaguar driveTrainJaguarRightFront;
    public static CANJaguar driveTrainJaguarLeftBack;
    public static CANJaguar driveTrainJaguarRightBack;
    public static RobotDrive driveTrainRobotDrive41;
    public static CANJaguar shooterShooterFrontWheel;
    public static CANJaguar shooterShooterBackWheel;
    public static CANJaguar shooterShooterAngle;
//    public static Encoder shooterShooterAngleEncoder;
    public static Relay acquisitionAcquisitionRoller;
    public static CANJaguar acquisitionAcquisitionScrewlift;
//    public static Encoder acquisitionAcquisitionScrewEncoder;
//    public static Relay climbClimbLeftWinch;
//    public static Relay climbClimbRightWinch;
//    public static Servo climbClimbReleaseLeftPin;
//    public static Servo climbClimbReleaseRightPin;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

     
    public static void init() {
        
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        try { 
            driveTrainJaguarLeftFront = new CANJaguar(2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap LeftFront");
        }
	
        
        try { 
            driveTrainJaguarLeftBack = new CANJaguar(3);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap LeftBack");
        }
	
        
        try { 
            driveTrainJaguarRightBack = new CANJaguar(4);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap RightBack");
        }
	
        
        try { 
            driveTrainJaguarRightFront = new CANJaguar(5);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap RightFront");
        }

        System.out.println("Robot Map init");
        try
        {
            System.out.println("Left Front Jaguar Control Mode" + " " + driveTrainJaguarLeftFront.getControlMode().toString());
            System.out.println("output current" + " " + driveTrainJaguarLeftFront.getOutputCurrent());
            Robot.debugOut("Left Front Jaguar Control Mode", driveTrainJaguarLeftFront.getControlMode().toString());
            Robot.debugOutNumber("outputCurrent", driveTrainJaguarLeftFront.getOutputCurrent());
            Robot.debugOutNumber("bus voltage",driveTrainJaguarLeftFront.getBusVoltage());
            Robot.debugOutNumber("output voltage", driveTrainJaguarLeftFront.getOutputVoltage() );
            
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        
        driveTrainRobotDrive41 = new RobotDrive(driveTrainJaguarLeftFront, driveTrainJaguarLeftBack,
              driveTrainJaguarRightFront, driveTrainJaguarRightBack);
	
        driveTrainRobotDrive41.setSafetyEnabled(false);
        driveTrainRobotDrive41.setExpiration(10);
        driveTrainRobotDrive41.setSensitivity(0.5);
        driveTrainRobotDrive41.setMaxOutput(1.0);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
//        shooterShooterFrontWheel = new Relay(1, 2);
//	LiveWindow.addActuator("Shooter", "Shooter FrontWheel", shooterShooterFrontWheel);
//        
//        shooterShooterBackWheel = new Relay(1, 1);
//	LiveWindow.addActuator("Shooter", "Shooter BackWheel", shooterShooterBackWheel);
        
        try { 
            shooterShooterBackWheel = new CANJaguar(6);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap ShooterBackwheel");
        }
        
	try { 
            shooterShooterFrontWheel = new CANJaguar(7);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap ShooterFrontwheel");
        }
        
        try {
            shooterShooterAngle = new CANJaguar(8);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap ShooterAngle");
        }
        
        try {
            acquisitionAcquisitionScrewlift = new CANJaguar(9);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            System.out.println("CAN Timeout Exception on RobotMap AcquisitionScrewlift");
        }
        
//        shooterShooterAngleEncoder = new Encoder(1, 3, 1, 4, false, EncodingType.k4X);
//	LiveWindow.addSensor("Shooter", "Shooter AngleEncoder", shooterShooterAngleEncoder);
//        shooterShooterAngleEncoder.setDistancePerPulse(1.0);
//        shooterShooterAngleEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
//        shooterShooterAngleEncoder.start();
//        acquisitionAcquisitionScrewlift = new Relay(1, 1);
//        LiveWindow.addActuator("Acquisition", "Acquisition Screwlift", acquisitionAcquisitionScrewlift);
        
        acquisitionAcquisitionRoller = new Relay(1, 1);
	LiveWindow.addActuator("Acquisition", "Acquisition Roller", acquisitionAcquisitionRoller);	
        
//        acquisitionAcquisitionScrewEncoder = new Encoder(1, 1, 1, 2, false, EncodingType.k4X);
//	LiveWindow.addSensor("Acquisition", "Acquisition ScrewEncoder", acquisitionAcquisitionScrewEncoder);
//        acquisitionAcquisitionScrewEncoder.setDistancePerPulse(1.0);
//        acquisitionAcquisitionScrewEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
//        acquisitionAcquisitionScrewEncoder.start();
//        climbClimbLeftWinch = new Relay(1, 3);
//	LiveWindow.addActuator("Climb", "Climb LeftWinch", climbClimbLeftWinch);
//        
//        climbClimbRightWinch = new Relay(1, 4);
//	LiveWindow.addActuator("Climb", "Climb RightWinch", climbClimbRightWinch);
//        
//        climbClimbReleaseLeftPin = new Servo(1, 1);
//	LiveWindow.addActuator("Climb", "Climb ReleaseLeftPin", climbClimbReleaseLeftPin);
//        
//        climbClimbReleaseRightPin = new Servo(1, 2);
//	LiveWindow.addActuator("Climb", "Climb ReleaseRightPin", climbClimbReleaseRightPin);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}

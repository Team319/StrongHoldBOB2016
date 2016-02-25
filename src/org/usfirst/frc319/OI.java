// Strong Hold BOB 2016 OI

package org.usfirst.frc319;

import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.arm.ArmDelayThenGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToAutoShootLowPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.arm.ArmGoToShootUnderTower;
import org.usfirst.frc319.commands.arm.ArmGoToStorage;
import org.usfirst.frc319.commands.auto.LowBarAuto;
import org.usfirst.frc319.commands.auto.LowBarLowGoalAuto;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorOut;
import org.usfirst.frc319.commands.collector.CollectorStop;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.shooter.ShooterGo;
import org.usfirst.frc319.commands.commandGroups.*;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveStraightSpline;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc319.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {    
    public BobController driverController;
    public BobController operatorController;  
    
    public OI() {

    	/*driverController = new BobController(0);
    	//driverController.startButton.whenPressed(new FollowBothMotionProfiles());
    	driverController.leftBumper.whenPressed(new CollectAndStop());
    	driverController.rightBumper.whenPressed(new SpeedUpThenShoot());
    	driverController.aButton.whenPressed(new CollectorOut());
    	driverController.bButton.whenPressed(new ArmGoToCollect());
    	driverController.xButton.whenPressed(new ArmGoToStorage());
    	driverController.yButton.whenPressed(new ArmGoToShootUnderTower());
    	driverController.startButton.whenPressed(new ShiftToggle());
    	driverController.selectButton.whenPressed(new CollectorAndShooterStop());
    	*/
    	 
    	
    	 driverController = new BobController(0);
    	driverController.startButton.whenPressed(new ArmDelayThenGoToCollect());
    	driverController.leftBumper.whenPressed(new CollectAndStop());
    	driverController.rightBumper.whenPressed(new SpeedUpThenShoot());
    	driverController.bButton.whenPressed(new CollectorAndShooterStop());
    	driverController.yButton.whenPressed(new BrakeModeToggle());
    	driverController.aButton.whenPressed(new CollectorOut());
    	driverController.xButton.whenPressed(new ShiftToggle());
    	
    	
    	operatorController = new BobController(1);
    	//operatorController.selectButton.whenPressed(new FollowBothMotionProfiles());
    	operatorController.startButton.whenPressed(new StopAllMechanisms());
    	
    	//operatorController.bButton.whenPressed(new AutoDriveStraightDistanceUsingRobotDrive());
    	operatorController.xButton.whenPressed(new ArmGoToCollectThenShootUnderTower());
    	operatorController.yButton.whenPressed(new ArmToCollectThenShootFromBatter());
    	operatorController.aButton.whenPressed(new ArmGoToCollect());  
    	operatorController.bButton.whenPressed(new ArmGoToStorage());
    	operatorController.leftBumper.whenPressed(new ShiftDown());
    	operatorController.rightBumper.whenPressed(new ShiftUp());
    	operatorController.rightTriggerButton.whenPressed(new ArmGoToAutoShootLowPosition());
    	//operatorController.rightBumper.whenPressed(new CollectorIn());
    	//operatorController.leftBumper.whenPressed(new CollectorOut());
    	//operatorController.aButton.whenPressed(new CollectorStop());
    	
      
    	//  operatorController.startButton.whenPressed(new ExampleCommandGroup());
    	
    	//MIKE'S CODE
    	/*driverController.selectButton.whenPressed(new DriveAutoSpline());
    	driverController.leftTriggerButton.whileHeld(new ShooterGo());
    	driverController.rightTriggerButton.whileHeld(new CollectorIn());
    	driverController.leftBumper.whileHeld(new CollectorOut());
    	driverController.rightBumper.whileHeld(new CollectorIn());
    	driverController.xButton.whenPressed(new ArmGoToShootUnderTower());
    	driverController.bButton.whenPressed(new ArmGoToCollect()); 
    	driverController.aButton.whenPressed(new ArmGoToStorage());
    	*/
    }

    public Joystick getDriverController() {
        return driverController;
        
    }

}


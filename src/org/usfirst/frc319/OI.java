 // Strong Hold BOB 2016 OI

package org.usfirst.frc319;

import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.arm.ArmDelayThenGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToAutoShootLowPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToLowGoal;
import org.usfirst.frc319.commands.arm.ArmGoToPassedVariablePosition;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.arm.ArmGoToShootUnderTower;
import org.usfirst.frc319.commands.arm.ArmGoToStorage;
import org.usfirst.frc319.commands.arm.ArmGoToVertical;
import org.usfirst.frc319.commands.auto.LowBarLowGoalAuto;
import org.usfirst.frc319.commands.camera.CalculateSpline;
import org.usfirst.frc319.commands.camera.CameraCalculateOffset;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.camera.TuneCameraRotate;
import org.usfirst.frc319.commands.collector.CenterBoulder;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorOut;
import org.usfirst.frc319.commands.collector.CollectorStop;
import org.usfirst.frc319.commands.collector.ForcedShot;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.shooter.ShooterGo;
import org.usfirst.frc319.commands.shooter.ShooterPIDTest;
import org.usfirst.frc319.commands.commandGroups.*;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveStraightSpline;
import org.usfirst.frc319.commands.drivetrain.RotateToAngle;
//import org.usfirst.frc319.commands.drivetrain.RotateToSquareWithField;








import org.usfirst.frc319.commands.drivetrain.RotateToAngle;

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
    	 driverController.startButton.whenPressed(new RotateToAngle(5));
    	 driverController.selectButton.whenPressed(new RotateToAngle(-5));
    	//driverController.startButton.whenPressed(new ShooterPIDTest());
    	driverController.leftBumper.whenPressed(new CollectAndStop());
    	driverController.rightBumper.whenPressed(new AutoAimShoot());
    	//driverController.rightBumper.whenPressed(new TowerShot());
    	driverController.bButton.whenPressed(new CollectorAndShooterStop());
    	driverController.yButton.whenPressed(new ForcedShot());
    	driverController.aButton.whenPressed(new CollectorOut());
    	driverController.xButton.whenPressed(new ShiftToggle());
    	//driverController.rightStickButton.whenPressed(new ());
    	driverController.leftStickButton.whenPressed(new BrakeModeToggle());
    	
    	operatorController = new BobController(1);
    	operatorController.selectButton.whenPressed(new ArcadeDrive());
    	operatorController.startButton.whenPressed(new StopAllMechanisms());

    	//operatorController.bButton.whenPressed(new AutoDriveStraightDistanceUsingRobotDrive());
    	operatorController.xButton.whenPressed(new CenterBoulderRoutine());
    	operatorController.yButton.whenPressed(new ArmGoToVertical());
    	operatorController.aButton.whenPressed(new ArmGoToCollect());
    	operatorController.bButton.whenPressed(new ArmGoToStorage());
    	operatorController.leftBumper.whenPressed(new CameraCalculateOffset());
    	operatorController.rightBumper.whenPressed(new LoadAndSpeedUp());
    	operatorController.rightTriggerButton.whenPressed(new ArmGoToShootFromBatterCleat());
    	operatorController.leftTriggerButton.whenPressed(new ArmGoToLowGoal());
    	//operatorController.leftTriggerButton.whenPressed(new RotateToAngle(10));
    	//operatorController.selectButton.whenPressed(new GoOverChevalleDeFrise());
    	//operatorController.leftBumper.whenPressed(new CollectorOut());
    	//operatorController.aButton.whenPressed(new CollectorStop());


    	//  operatorController.startButton.whenPressed(new ExampleCommandGroup());

    	//MIKE'S CODE
    	driverController.selectButton.whenPressed(new TuneCameraRotate());
    	driverController.startButton.whenPressed(new ArcadeDrive());
    	/*
    	driverController = new BobController(0);
    	driverController.leftBumper.whenPressed(new CollectAndStop());
    	//driverController.rightBumper.whenPressed(new SpeedUpThenShoot());
    	driverController.aButton.whenPressed(new ArmGoToCollect());
    	driverController.bButton.whenPressed(new CollectorStop());
    	driverController.xButton.whenPressed(new ArmGoToShootFromBatterCleat());
    	driverController.yButton.whenPressed(new ArmGoToStorage());
    	driverController.startButton.whenPressed(new ShiftToggle());
    	driverController.selectButton.whenPressed(new CollectorAndShooterStop());
    	driverController.rightTriggerButton.whenPressed(new AlignThenSpeedUpThenShoot());
    	*/
    }

    public Joystick getDriverController() {
        return driverController;

    }

}


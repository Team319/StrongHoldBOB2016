package org.usfirst.frc319.commands.commandGroups;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmAdjustToTarget;
import org.usfirst.frc319.commands.arm.ArmAutoAdjust;
import org.usfirst.frc319.commands.arm.ArmGoToAutoSearchPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToPassedVariablePosition;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromOuterworks;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromTape;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.AlignThenSpeedUpThenShoot;
import org.usfirst.frc319.commands.commandGroups.AutoAimShoot;
import org.usfirst.frc319.commands.commandGroups.LoadAndSpeedUpAuto;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.BuildPosition2Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition3Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition4Spline;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;
import org.usfirst.frc319.commands.drivetrain.RotateToFaceOurOwnTower;
import org.usfirst.frc319.commands.drivetrain.RotateToSquareWithField;
import org.usfirst.frc319.commands.drivetrain.TeleopAimRotate;
import org.usfirst.frc319.commands.shooter.ShooterSpeedUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimAtTarget extends CommandGroup {
	public AimAtTarget(){
		//addParallel(new ArmAdjustToTarget());
		addParallel(new ArmAutoAdjust());
		addSequential(new TeleopAimRotate());
		
}
}

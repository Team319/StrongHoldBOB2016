package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmGoToAutoSearchPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromTape;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.AutoAimShoot;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.BuildPosition2Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition3Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition4Spline;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;
import org.usfirst.frc319.commands.drivetrain.RotateToSquareWithField;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Position2VariousAuto extends CommandGroup {
	public Position2VariousAuto(){
		

		
		addSequential(new AutoDriveStraightDistanceUsingRobotDrive());
		addSequential(new RotateToSquareWithField());
		//addSequential(new BuildPosition2Spline());
		//addSequential(new DriveSpline());
		addSequential(new ArmGoToAutoSearchPosition());
		//addSequential(new ArmGoToShootFromTape());
		//addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new AutoAimShoot());
		
	
		/**
		addSequential(new AutoDriveStraightDistanceUsingRobotDrive());
		addSequential(new BuildPosition4Spline());
		addSequential(new DriveSpline());
		//addSequential(new ArmGoToAutoSearchPosition());
		//addSequential(new CameraDrive());
		//addSequential(new RotateToTarget());
		addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new SpeedUpThenShoot());
		**/
		
		
	}
}

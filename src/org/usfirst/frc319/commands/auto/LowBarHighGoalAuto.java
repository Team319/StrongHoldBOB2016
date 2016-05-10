  package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.ShiftDown;
import org.usfirst.frc319.commands.arm.ArmDelayThenGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToAutoSearchPosition;
import org.usfirst.frc319.commands.arm.ArmGoToAutoShootHighPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.AlignThenSpeedUpThenShoot;
import org.usfirst.frc319.commands.commandGroups.AutoAimShoot;
import org.usfirst.frc319.commands.commandGroups.LoadAndSpeedUp;
import org.usfirst.frc319.commands.commandGroups.LoadAndSpeedUpAuto;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightSlow;
import org.usfirst.frc319.commands.drivetrain.BuildPosition1Spline;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;
import org.usfirst.frc319.commands.drivetrain.RotateToSquareWithField;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarHighGoalAuto extends CommandGroup {
	public LowBarHighGoalAuto(){  
		//addSequential(new BuildSingleTowerSpline());
		addSequential(new ShiftDown());
		addParallel(new ArmGoToCollect());
		addSequential(new AutoDriveStraightDistanceUsingRobotDrive());
		addSequential(new RotateToSquareWithField());
		
		//addParallel(new ArmGoToCollect());//OPEN TO CHANGING THIS TO SEQUENTIAL Derrick 3/7/16

		addSequential(new BuildPosition1Spline());
		addSequential(new DriveSpline());
	//	addParallel(new LoadAndSpeedUpAuto());
		addSequential(new ArmGoToAutoSearchPosition());
		//addSequential(new ArmGoToShootFromBatterCleat());
		addSequential (new SpeedUpThenShoot());
		//addSequential (new AutoAimShoot());
	}
}

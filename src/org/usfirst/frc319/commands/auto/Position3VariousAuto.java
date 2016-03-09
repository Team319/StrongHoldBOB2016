package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmGoToAutoSearchPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.AlignThenSpeedUpThenShoot;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.drivetrain.BuildPosition2Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition3Spline;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;
import org.usfirst.frc319.commands.drivetrain.RotateToSquareWithField;
import org.usfirst.frc319.commands.shooter.ShooterSpeedUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Position3VariousAuto extends CommandGroup {
	public Position3VariousAuto(){
	
		addSequential(new AutoDriveStraightDistanceUsingRobotDrive());
		addSequential(new RotateToSquareWithField());
		addSequential(new BuildPosition3Spline());
		addSequential(new DriveSpline());
		addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new AlignThenSpeedUpThenShoot());
		
		
}
}

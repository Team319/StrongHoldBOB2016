package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmDelayThenGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToAutoShootLowPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarLowGoalAuto extends CommandGroup {
	public LowBarLowGoalAuto(){

		addSequential(new BuildSingleTowerSpline());
		addParallel(new ArmGoToCollect());
		addSequential(new DriveSpline());
		addSequential(new ArmGoToAutoShootLowPosition());
		addSequential(new SpeedUpThenShoot());
		
		
		
	}
}

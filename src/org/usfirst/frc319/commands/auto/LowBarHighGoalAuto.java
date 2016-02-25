package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmDelayThenGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToAutoShootHighPosition;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveAutoSpline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarHighGoalAuto extends CommandGroup {
	public LowBarHighGoalAuto(){
	
		
		addSequential(new BuildSingleTowerSpline());
		addParallel(new ArmDelayThenGoToCollect());
		addSequential(new DriveSpline());
		addSequential(new ArmGoToAutoShootHighPosition());
		addSequential(new SpeedUpThenShoot());
		
		
		
	}
}

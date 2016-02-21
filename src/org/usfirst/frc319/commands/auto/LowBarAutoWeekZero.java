package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAutoWeekZero extends CommandGroup {
	public LowBarAutoWeekZero(){
	
		addParallel(new ArmGoToCollect());
		addSequential(new BuildSingleTowerSpline());
		addSequential(new DriveAutoSpline());
		addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new SpeedUpThenShoot());
		
		
		
	}
}

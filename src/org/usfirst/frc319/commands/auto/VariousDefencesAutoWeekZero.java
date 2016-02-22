package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.FollowBothMotionProfiles;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;
import org.usfirst.frc319.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VariousDefencesAutoWeekZero extends CommandGroup {
	public VariousDefencesAutoWeekZero(){
	
		addSequential(new AutoDriveStraightDistanceUsingRobotDrive());
		/*addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new SpeedUpThenShoot());
		*/
	}
}

package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.commands.arm.ArmGoToShootFromBatterCleat;
import org.usfirst.frc319.commands.commandGroups.AutoAimShoot;
import org.usfirst.frc319.commands.commandGroups.SpeedUpThenShoot;
import org.usfirst.frc319.commands.drivetrain.BuildPosition2Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition3Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition4Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition5Spline;
import org.usfirst.frc319.commands.drivetrain.DriveSpline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveStraightThenSplineThenShoot extends CommandGroup {
	public AutoDriveStraightThenSplineThenShoot(String defense, String position){
		//DRIVE STRAIGHT
		addSequential(new AutoDriveStraightDistance(defense));

		//BUILD SPLINE
		if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_2)){
			addSequential(new BuildPosition2Spline());
		}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_3)){
			addSequential(new BuildPosition3Spline());
		}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_4)){
			addSequential(new BuildPosition4Spline());
		}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_5)){
			addSequential(new BuildPosition5Spline());
		}

		//DRIVE SPLINE
		addSequential(new DriveSpline());

		//SHOOT
		addSequential(new ArmGoToShootFromBatterCleat());
		addSequential(new AutoAimShoot());



	}
}

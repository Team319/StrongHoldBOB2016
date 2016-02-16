package org.usfirst.frc319.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAutoSpline extends CommandGroup{
	public DriveAutoSpline() {
		//addSequential(new BuildDriveStraightSpline());
		//addSequential(new DriveSpline());
		//addSequential(new BuildLeftTowerSpline());
		
		//pass a set a of waypoints to the sever
		//wait for response
		//load trajectory into drivetrain
		addSequential(new BuildSingleTowerSpline());
		
		//run the trajectory (stored in the drivetrain from the server) 
		addSequential(new DriveSpline());
	}
}

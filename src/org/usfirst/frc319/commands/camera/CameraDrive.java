package org.usfirst.frc319.commands.camera;

import org.usfirst.frc319.commands.drivetrain.DriveSpline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CameraDrive extends CommandGroup {
	public CameraDrive(){
		addSequential(new CalculateSpline());
		addSequential(new DriveSpline());
	}
}

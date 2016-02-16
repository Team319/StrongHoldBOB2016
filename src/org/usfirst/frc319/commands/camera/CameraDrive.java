package org.usfirst.frc319.commands.camera;

import org.usfirst.frc319.commands.auto.DriveSpline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CameraDrive extends CommandGroup {
	public CameraDrive(){
		addSequential(new CalculateSpline());
		addSequential(new DriveSpline());
	}
}

package org.usfirst.frc319.commands.camera;

import org.usfirst.frc319.commands.drivetrain.TuneRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TuneCameraRotate extends CommandGroup{
	public TuneCameraRotate(){
		this.addSequential(new CameraCalculateOffset());
		this.addSequential(new TuneRotate());
	}
}

package org.usfirst.frc319.commands.camera;

import org.usfirst.frc319.commands.arm.ArmAutoAdjust;
import org.usfirst.frc319.commands.drivetrain.TuneRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TuneCameraRotate extends CommandGroup{
	public TuneCameraRotate(){
		this.addSequential(new CameraCalculateOffset());
		//this.addParallel(new ArmAutoAdjust());
		this.addSequential(new TuneRotate());
	}
}

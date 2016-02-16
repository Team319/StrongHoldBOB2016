package org.usfirst.frc319.commands.camera;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.subsystems.TowerCamera;

import edu.wpi.first.wpilibj.command.Command;

public class RunTowerCamera extends Command{
	
	public RunTowerCamera() {
		requires(Robot.towerCamera);
	}

	@Override
	protected void initialize() {
		//Robot.towerCamera.initialize();
	}

	@Override
	protected void execute() {
		//Robot.towerCamera.run();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}

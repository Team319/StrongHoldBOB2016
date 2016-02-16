package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command{
	public Shoot(){
		requires(Robot.collector);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.collector.shoot();
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

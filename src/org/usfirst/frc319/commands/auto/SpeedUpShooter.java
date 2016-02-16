package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpeedUpShooter extends Command {
	
	private long startTime;
	
	public SpeedUpShooter(){
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.shooter.speedUp();;
	}

	@Override
	protected boolean isFinished() {
		//have we been running for a second
		return System.currentTimeMillis() - startTime > 1000;
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

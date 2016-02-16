package org.usfirst.frc319.commands.collector;

import org.usfirst.frc319.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorTimedPause extends Command {
	
	private long startTime;
	
	public CollectorTimedPause(){
		requires(Robot.collector);
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.collector.stop();
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

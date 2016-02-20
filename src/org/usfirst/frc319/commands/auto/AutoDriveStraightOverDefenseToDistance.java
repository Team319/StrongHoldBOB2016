package org.usfirst.frc319.commands.auto;

import org.usfirst.frc319.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class AutoDriveStraightOverDefenseToDistance extends PIDCommand {

///------FOR NOW DON'TUSE THIS ONE - ROBOT DRIVE WORKS BETTER-------- 2/19/15//
	
	double driveSpeed= 0;
	double desiredDistance = 0;
	double initialDistance = 0;
	
	
	public AutoDriveStraightOverDefenseToDistance(double speed, double distance){
		
		//super("AutoDriveStraight",0.25,0.0,0.0,0.02);
		super("AutoDriveStraight",0.2,0.0,0.0,0.02);
		
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(1.0);
		
		driveSpeed = speed;
		desiredDistance = distance;
		initialDistance = Robot.driveTrain.getDistanceFromEncoderValues();
		
		requires(Robot.driveTrain);
	}


	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.driveTrain.getGyroAngle();
	}


	@Override
	protected void usePIDOutput(double output) {
		Robot.driveTrain.arcadeDrive(driveSpeed, output);
		
	}


	@Override
	protected void initialize() {
		this.setSetpoint(Robot.driveTrain.getGyroAngle());
		this.getPIDController().enable();
		Robot.driveTrain.setDTEncodersToZero();
		Robot.driveTrain.shiftDown();
	}


	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected boolean isFinished() {
		if(Robot.driveTrain.getDistanceFromEncoderValues()> 50000){
		return true;
	}
		else{
			System.out.println("not at distance");
			System.out.println("robot distance" +Robot.driveTrain.getDistanceFromEncoderValues());
			return false;
		}
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

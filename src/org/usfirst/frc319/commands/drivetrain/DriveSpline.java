package org.usfirst.frc319.commands.drivetrain;

import org.usfirst.frc319.GeneratedMotionProfile;
import org.usfirst.frc319.LeftMotionProfile;
import org.usfirst.frc319.RightMotionProfile;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class DriveSpline extends Command{
	
	private long startTime = 0;
	private long maxTime = 0;
	
	int loops = 0;

	//CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
	//CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
	
	boolean motionProfileStarted = true;
	//true indicates left profile
	GeneratedMotionProfile leftProfile = new GeneratedMotionProfile(RobotMap.driveTrainleftDriveLead, true);
	//false indicates right profile
	GeneratedMotionProfile rightProfile = new GeneratedMotionProfile(RobotMap.driveTrainrightDriveLead, false);
	
	
	public DriveSpline() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		/**
		//calculate the time it should take , used as an override to end the MP command based on a time
		 
		int leftCount = Robot.driveTrain.getCurrentProfile().getLeftProfile().getNumPoints();
		int rightCount = Robot.driveTrain.getCurrentProfile().getRightProfile().getNumPoints();
		
		maxTime = Math.max(leftCount, rightCount) * 10; //in ms
		startTime = System.currentTimeMillis();
		**/
		
		Robot.driveTrain.shiftDown();
	    loops = 0;
	    
	    rightProfile.reset();
	    leftProfile.reset();
	    
	    motionProfileStarted = true;
	}

	@Override
	protected void execute() {
		rightProfile.control();
    	leftProfile.control();
		
    	RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	
    	CANTalon.SetValueMotionProfile setRightOutput = rightProfile.getSetValue();
        CANTalon.SetValueMotionProfile setLeftOutput = leftProfile.getSetValue();
    	
        RobotMap.driveTrainrightDriveLead.set(setRightOutput.value);
    	RobotMap.driveTrainleftDriveLead.set(setLeftOutput.value);
    	
    	if(motionProfileStarted){
        	rightProfile.startMotionProfile();
        	leftProfile.startMotionProfile();
        		
        	motionProfileStarted = false;
    	}
	}

	@Override
	protected boolean isFinished() {
		//for testing purposes
		/**
		if(true){
			return true;
		}
		**/
		/**
		if(System.currentTimeMillis() - startTime > maxTime){
			return true;
		}else 
		**/
			
		if( rightProfile.getTimeoutCnt() >2 || leftProfile.getTimeoutCnt() >2){
	    	return true;
    	}else if (rightProfile.isFinished()==true && leftProfile.isFinished()==true){
	        return true;
    	}else{
	    	return false;
    	}
	}

	@Override
	protected void end() {
		RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightDriveFollow.changeControlMode(TalonControlMode.Follower);
    	RobotMap.driveTrainrightDriveFollow.set(RobotMap.driveTrainrightDriveLead.getDeviceID());
     	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
     	RobotMap.driveTrainleftDriveFollow.changeControlMode(TalonControlMode.Follower);
     	RobotMap.driveTrainleftDriveFollow.set(RobotMap.driveTrainleftDriveLead.getDeviceID());
    	
    	
    	RobotMap.driveTrainleftDriveLead.set(0);
    	RobotMap.driveTrainrightDriveLead.set(0);
    	
    	rightProfile.reset();
    	leftProfile.reset();
    	
    	//Our profile has been run, so let's empty it
    	Robot.driveTrain.setCurrentProfile(null);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}

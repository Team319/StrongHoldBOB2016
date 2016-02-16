// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.LeftMotionProfile;
import org.usfirst.frc319.RightMotionProfile;
import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;
import org.usfirst.frc319.motionProfiles.*;

/**
 *
 */
public class FollowBothMotionProfiles extends Command {
	
	int loops = 0;

	//CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
	//CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
	
	boolean motionProfileStarted = true;
	
	LeftMotionProfile leftProfile = new LeftMotionProfile(RobotMap.driveTrainleftDriveLead);
    RightMotionProfile rightProfile = new RightMotionProfile(RobotMap.driveTrainrightDriveLead);
	
    public FollowBothMotionProfiles() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    Robot.driveTrain.shiftDown();
    loops = 0;
//    Robot.driveTrain.enableMotionProfileMode();
    /*
    RobotMap.driveTrainrightDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    RobotMap.driveTrainrightDriveLead.configEncoderCodesPerRev(1024);
    RobotMap.driveTrainrightDriveLead.reverseSensor(false);
    RobotMap.driveTrainrightDriveLead.reverseOutput(true);
    
    RobotMap.driveTrainrightDriveLead.setF(0.312);
    RobotMap.driveTrainrightDriveLead.setP(0);//0.28);
    
    RobotMap.driveTrainleftDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    RobotMap.driveTrainleftDriveLead.configEncoderCodesPerRev(1024);
    RobotMap.driveTrainleftDriveLead.reverseSensor(true);
    
    RobotMap.driveTrainleftDriveLead.setF(0.333);
    RobotMap.driveTrainleftDriveLead.setP(0);//0.28);
    */
    
    rightProfile.reset();
    leftProfile.reset();
    
    motionProfileStarted = true;
    
    System.out.println("initialized");
    //Robot.driveTrain.setRightEncoderToZero();//No good
    }

    protected void execute() {
    	rightProfile.control();
    	leftProfile.control();
    	
    	//Robot.driveTrain.rightFollowMotionProfile();
    	//Robot.driveTrain.leftFollowMotionProfile();
    	//System.out.println("Motion Profilestarted: " +motionProfileStarted);
    	RobotMap.driveTrainrightDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.driveTrainleftDriveLead.changeControlMode(TalonControlMode.MotionProfile);
    	
    	CANTalon.SetValueMotionProfile setRightOutput = rightProfile.getSetValue();
        CANTalon.SetValueMotionProfile setLeftOutput = leftProfile.getSetValue();
    	
        RobotMap.driveTrainrightDriveLead.set(setRightOutput.value);
    	RobotMap.driveTrainleftDriveLead.set(setLeftOutput.value);
    	
    	
    	
    	
    	if(motionProfileStarted){
    	rightProfile.startMotionProfile();
    	leftProfile.startMotionProfile();
    		
    	//Robot.driveTrain.startRightMotionProfile();
    	//Robot.driveTrain.startLeftMotionProfile();
    	motionProfileStarted = false;
    	}
    	
    	//System.out.println("Executing");
    }

    protected boolean isFinished() {
    	
    	if( rightProfile.getTimeoutCnt() >2 || leftProfile.getTimeoutCnt() >2){
    	return true;
    	}
    	else if (rightProfile.isFinished()==true && leftProfile.isFinished()==true){
    		System.out.println("Command Finished");
        return true;
    	}
    	else{
    	return false;
    	}
    	
    	//return Robot.driveTrain.isLeftMotionProfileFinished();
    	//return Robot.driveTrain.shift;
    	
    }

    protected void end() {
    	System.out.println("Ended");
    	
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
    	
    	//Robot.driveTrain.resetRightMotionProfile();
    	//Robot.driveTrain.resetLeftMotionProfile();
    	
    	
    	
    }

    protected void interrupted() {
    	 System.out.println("interrupted");
    }
    
}

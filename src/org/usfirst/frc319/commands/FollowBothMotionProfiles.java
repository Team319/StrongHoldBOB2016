// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.CANTalon;
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

	CANTalon rightDriveLead = RobotMap.driveTrainrightDriveLead;
	CANTalon leftDriveLead = RobotMap.driveTrainleftDriveLead;
	
	boolean motionProfileStarted = true;
	
	LeftMotionProfile leftProfile = new LeftMotionProfile(leftDriveLead);
    RightMotionProfile rightProfile = new RightMotionProfile(rightDriveLead);
	
    public FollowBothMotionProfiles() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    
    loops = 0;
    Robot.driveTrain.enableMotionProfileMode();
    
    rightProfile.reset();
    leftProfile.reset();
    
    motionProfileStarted = true;
    
    System.out.println("initialized");
    //Robot.driveTrain.setRightEncoderToZero();//No good
    }

    protected void execute() {
    	rightProfile.control();
    	leftProfile.control();
    	
    	Robot.driveTrain.rightFollowMotionProfile();
    	Robot.driveTrain.leftFollowMotionProfile();
    	//System.out.println("Motion Profilestarted: " +motionProfileStarted);
    	
    	if(motionProfileStarted){
    	Robot.driveTrain.startRightMotionProfile();
    	Robot.driveTrain.startLeftMotionProfile();
    	motionProfileStarted = false;
    	}
    	
    	System.out.println("Executing");
    }

    protected boolean isFinished() {
    	
    	if( Robot.driveTrain.getRightTimeoutCnt() >2 || Robot.driveTrain.getLeftTimeoutCnt()>2){
    	return true;
    	}
    	else if (rightProfile.isFinished()==true && leftProfile.isFinished()==true){
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
    	Robot.driveTrain.setModeToVBus();
    	leftDriveLead.set(0);
    	rightDriveLead.set(0);
    	Robot.driveTrain.resetRightMotionProfile();
    	Robot.driveTrain.resetLeftMotionProfile();
    	
    	
    	
    }

    protected void interrupted() {
    	 System.out.println("interrupted");
    }
    
}

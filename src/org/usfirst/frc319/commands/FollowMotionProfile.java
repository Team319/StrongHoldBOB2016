// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class FollowMotionProfile extends Command {
	
	int loops = 0;

	public boolean motionProfileStarted;
	
	
	
    public FollowMotionProfile() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    motionProfileStarted = false;	
    System.out.println("initialized");
    //Robot.driveTrain.setRightEncoderToZero();//No good
    }

    protected void execute() {
    	Robot.driveTrain.enableMotionProfileMode();
    	Robot.driveTrain.rightFollowMotionProfile();
    	//Robot.driveTrain.leftFollowMotinProfile();
    	//System.out.println("Motion Profilestarted: " +motionProfileStarted);
    	
    	if(motionProfileStarted == false){
    	Robot.driveTrain.startRightMotionProfile();
    	//Robot.driveTrain.startLeftMotionProfile();
    	motionProfileStarted = true;
    	}
    	
    	System.out.println("Executing");
    }

    protected boolean isFinished() {
    	return Robot.driveTrain.isRightMotionProfileFinished();
    	//return Robot.driveTrain.isLeftMotionProfileFinished();
    	//return Robot.driveTrain.shift;
    	
    }

    protected void end() {
    	 System.out.println("Ended");
    	Robot.driveTrain.resetRightMotionProfile();
    	//Robot.driveTrain.resetLeftMotionProfile();
    	
    	Robot.driveTrain.setModeToVBus();
    	
    }

    protected void interrupted() {
    	 System.out.println("interrupted");
    }
    
}

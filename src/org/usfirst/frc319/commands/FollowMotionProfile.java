// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class FollowMotionProfile extends Command {

   
    public FollowMotionProfile() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    	Robot.driveTrain.enableMotionProfileMode();
    	Robot.driveTrain.startMotionProfile();
    }

    protected void execute() {
    	Robot.driveTrain.followMotionProfile();
    }

    protected boolean isFinished() {
    	return Robot.driveTrain.isMotionProfileFinished();
    	
    }

    protected void end() {
    	Robot.driveTrain.resetMotionProfile();
    	
    }

    protected void interrupted() {
    }
    
}

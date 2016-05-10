// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmAdjustToTarget extends Command {

    public ArmAdjustToTarget() {

        requires(Robot.arm);

    }

    protected void initialize() {
    	double currentDegrees = Robot.arm.getArmDegrees();
    	double targetErrorDegrees = Robot.arm.getVerticalOffsetError();
    	Robot.arm.setArmPositionDegrees(currentDegrees - targetErrorDegrees);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.arm.isOnTarget();
        
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

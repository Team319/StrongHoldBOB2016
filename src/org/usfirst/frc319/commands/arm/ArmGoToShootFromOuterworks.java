// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToShootFromOuterworks extends Command {

    public ArmGoToShootFromOuterworks() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.shooter.setSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_HIGH_SPEED_KEY));
    	Robot.arm.setArmPosition(Robot.constants.getConstant(BobConstants.ARM_OUTERWORKS_POS_KEY));//goToCleatPosition();
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

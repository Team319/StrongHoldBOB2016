// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToAutoShootLowPosition extends Command {

    public ArmGoToAutoShootLowPosition() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.setArmPosition(Robot.constants.getConstant(BobConstants.ARM_AUTO_SHOOT_LOW_POS_KEY));//goToAutoShootLowPosition();
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

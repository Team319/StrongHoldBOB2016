// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToStorage extends Command {

    public ArmGoToStorage() {

        requires(Robot.arm);

    }

    protected void initialize() {
    	Robot.arm.setArmPosition(Robot.constants.getConstant(BobConstants.ARM_STORAGE_POS_KEY));//goToStorage();
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

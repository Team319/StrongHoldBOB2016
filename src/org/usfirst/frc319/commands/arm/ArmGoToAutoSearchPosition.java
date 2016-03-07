// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToAutoSearchPosition extends Command {

	
    public ArmGoToAutoSearchPosition() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	//						 and from the appropriate dictionary, ( select the appropriate constant)
    	Robot.arm.setArmPosition(Robot.constants.getConstant(BobConstants.ARM_AUTO_SEARCH_POS_KEY));//goToAutoSearchPosition();
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

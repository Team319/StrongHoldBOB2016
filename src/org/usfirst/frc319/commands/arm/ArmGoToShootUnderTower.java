// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToShootUnderTower extends Command {

    public ArmGoToShootUnderTower() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.shooter.setSpeed(Robot.constants.getConstant(BobConstants.SHOOTER_UNDER_TOWER_SPEED_KEY));
    	Robot.arm.setArmPosition(Robot.constants.getConstant(BobConstants.ARM_SHOOT_FROM_TOWER_POS_KEY));//gotToShootFromTower();
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

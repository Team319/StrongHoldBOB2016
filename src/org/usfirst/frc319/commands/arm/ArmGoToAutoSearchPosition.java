// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToAutoSearchPosition extends Command {

	
    public ArmGoToAutoSearchPosition() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.goToAutoSearchPosition();
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

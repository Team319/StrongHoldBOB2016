// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToAutoShootPosition extends Command {

    public ArmGoToAutoShootPosition() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.goToAutoShootPosition();
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

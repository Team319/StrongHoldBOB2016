// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmGoToCollect extends Command {

    public ArmGoToCollect() {

        requires(Robot.arm);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.goToCollect();
    }

    protected boolean isFinished() {
        return Robot.arm.isOnTarget();
        
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

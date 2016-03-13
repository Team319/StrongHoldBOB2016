// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmStop extends Command {

    public ArmStop() {

        requires(Robot.arm);

    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	Robot.arm.armStop();	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

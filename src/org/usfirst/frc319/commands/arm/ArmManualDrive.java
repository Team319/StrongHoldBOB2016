// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmManualDrive extends Command {

    public ArmManualDrive() {

        requires(Robot.arm);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.armManualDrive(-Robot.oi.driverController.getTriggerTwist());
    							// negative added when encoder on arm moved 3/3/16
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

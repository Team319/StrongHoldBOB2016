// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands;

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
    	double leftTriggerValue = -Robot.oi.xBoxController.getRawAxis(2);
    	double rightTriggerValue = Robot.oi.xBoxController.getRawAxis(3);
    
    	double triggerValue = leftTriggerValue + rightTriggerValue;
    	Robot.arm.armManualDrive(triggerValue);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

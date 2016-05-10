// Strong Hold BOB 2016 Commands (Shift Toggle)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class FlashLightOff extends Command {

    public FlashLightOff() {

        requires(Robot.arm);

    }

    protected void initialize() {
    
    	Robot.arm.turnOffFlashLight();
    
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

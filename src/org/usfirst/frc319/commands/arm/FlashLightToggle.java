// Strong Hold BOB 2016 Commands (Shift Toggle)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class FlashLightToggle extends Command {

    public FlashLightToggle() {

        requires(Robot.arm);

    }

    protected void initialize() {
    	if (Robot.arm.flash == true)
    	{
    	Robot.arm.turnOnFLashLight();

    	}
    	else if(Robot.arm.flash == false){
    	Robot.arm.turnOffFlashLight();
    
    	}
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

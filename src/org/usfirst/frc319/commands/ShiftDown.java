// Strong Hold BOB 2016 Commands (Shift Toggle)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShiftDown extends Command {

    public ShiftDown() {

        requires(Robot.driveTrain);

    }

    protected void initialize() {
    	Robot.driveTrain.shiftUp();
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

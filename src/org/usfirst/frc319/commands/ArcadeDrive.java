// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArcadeDrive extends Command {

   
    public ArcadeDrive() {
  
        requires(Robot.driveTrain);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double moveValue = Robot.oi.driverController.getLeftStickY();
    	double rotateValue = Robot.oi.driverController.getRightStickX();
    	
    	Robot.driveTrain.arcadeDrive(moveValue, rotateValue);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

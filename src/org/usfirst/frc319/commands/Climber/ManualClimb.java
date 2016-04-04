// Strong Hold BOB 2016 Commands (Arcade Drive)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.RobotMap;

/**
 *
 */
public class ManualClimb extends Command {

   
    public ManualClimb() {
  
        requires(Robot.climber);

    }

    protected void initialize() {
    }

    protected void execute() {
    
    	Robot.climber.manualClimb(Robot.oi.operatorController.getLeftStickY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

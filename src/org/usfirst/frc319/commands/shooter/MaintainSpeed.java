// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class MaintainSpeed extends Command {

    public MaintainSpeed() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = Robot.shooter.getLeftShooterSetpoint();
    	Robot.shooter.setLeftShooterSpeed(speed);
    	Robot.shooter.setRightShooterSpeed(speed);
    	
    }

    protected boolean isFinished() {
        return false;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

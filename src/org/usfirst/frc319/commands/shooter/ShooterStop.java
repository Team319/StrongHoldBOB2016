// Strong Hold BOB 2016 Commands (Shooter Stop)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShooterStop extends Command {

    public ShooterStop() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	Robot.shooter.setLeftShooterStop();
    	Robot.shooter.setRightShooterStop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

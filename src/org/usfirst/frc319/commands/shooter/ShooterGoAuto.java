// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc319.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShooterGoAuto extends Command {

    public ShooterGoAuto() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = 4000;
    	Robot.shooter.setLeftShooterSpeed(speed);
    	Robot.shooter.setRightShooterSpeed(speed);
    	
    }

    protected boolean isFinished() {
        return true;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

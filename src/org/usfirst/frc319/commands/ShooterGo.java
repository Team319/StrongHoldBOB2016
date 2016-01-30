// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ShooterGo extends Command {

    public ShooterGo() {

        requires(Robot.shooter);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = 3000;
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

// Strong Hold BOB 2016 Commands (Shooter Stop)

package org.usfirst.frc319.commands;

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
    	Robot.shooter.leftShooterStop();
    	Robot.shooter.rightShooterStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
